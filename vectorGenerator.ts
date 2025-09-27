import { LocalEmbedder } from './modelSelector';
import { preprocessText } from './textPreprocessor';

/**
 * 生成文本向量（整合预处理、模型调用、归一化）
 * @param text 原始文本
 * @returns 归一化后的向量
 */
export async function generateVector(text: string): Promise<number[]> {
  // 1. 预处理文本（确保输入一致性）
  const processedText = preprocessText(text);

  // 2. 单例模式获取模型（避免重复加载）
  const embedder = await LocalEmbedder.getInstance();

  // 3. 生成向量（模型内部已做归一化）
  const vector = await embedder.embed(processedText);

  // 4. 二次校验归一化（确保向量模长≈1，误差<1e-6）
  const norm = Math.sqrt(vector.reduce((sum, val) => sum + val * val, 0));
  if (Math.abs(norm - 1) > 1e-6) {
    // 手动归一化修正
    const normalized = vector.map(v => v / norm);
    console.warn('向量未归一化，已自动修正');
    return normalized;
  }

  return vector;
}

// 验证一致性：相同文本生成的向量应高度相似
async function testVectorConsistency() {
  const text = '地球的平均半径约为6371公里';
  const vec1 = await generateVector(text);
  const vec2 = await generateVector(text);
  
  // 计算余弦相似度（应接近1）
  const similarity = vec1.reduce((sum, v1, i) => sum + v1 * vec2[i], 0);
  console.log(`相同文本向量相似度：${similarity.toFixed(6)}`); // 预期 >0.999
}

testVectorConsistency();
