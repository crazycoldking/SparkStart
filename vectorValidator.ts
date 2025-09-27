import { generateVector } from './vectorGenerator';

/**
 * 计算两个向量的余弦相似度
 */
export function cosineSimilarity(vec1: number[], vec2: number[]): number {
  if (vec1.length !== vec2.length) throw new Error('向量维度不一致');
  let dotProduct = 0;
  let norm1 = 0;
  let norm2 = 0;
  for (let i = 0; i < vec1.length; i++) {
    dotProduct += vec1[i] * vec2[i];
    norm1 += vec1[i] * vec1[i];
    norm2 += vec2[i] * vec2[i];
  }
  return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
}

/**
 * 验证向量对已知语义关系的捕捉能力
 */
export async function validateSemanticCapture() {
  // 测试用例：语义相似文本对
  const similarPairs = [
    { text1: '地球半径约6371公里', text2: '地球平均半径大概6371千米' },
    { text1: '电脑蓝屏可能是驱动问题', text2: '驱动错误可能导致电脑蓝屏' }
  ];

  // 测试用例：语义不相似文本对
  const dissimilarPairs = [
    { text1: '地球半径约6371公里', text2: '猫是常见的宠物' },
    { text1: '电脑蓝屏原因', text2: '如何制作蛋糕' }
  ];

  // 验证相似文本对的相似度（应≥0.8）
  for (const pair of similarPairs) {
    const vec1 = await generateVector(pair.text1);
    const vec2 = await generateVector(pair.text2);
    const sim = cosineSimilarity(vec1, vec2);
    if (sim < 0.8) {
      console.warn(`相似文本对验证失败："${pair.text1}" 与 "${pair.text2}"，相似度：${sim.toFixed(2)}`);
    } else {
      console.log(`相似文本对验证通过，相似度：${sim.toFixed(2)}`);
    }
  }

  // 验证不相似文本对的相似度（应≤0.3）
  for (const pair of dissimilarPairs) {
    const vec1 = await generateVector(pair.text1);
    const vec2 = await generateVector(pair.text2);
    const sim = cosineSimilarity(vec1, vec2);
    if (sim > 0.3) {
      console.warn(`不相似文本对验证失败："${pair.text1}" 与 "${pair.text2}"，相似度：${sim.toFixed(2)}`);
    } else {
      console.log(`不相似文本对验证通过，相似度：${sim.toFixed(2)}`);
    }
  }
}

// 执行验证
validateSemanticCapture();
