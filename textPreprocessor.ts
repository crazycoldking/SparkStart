/**
 * 标准化文本，去除噪声，确保输入一致性
 * @param text 原始文本
 * @returns 预处理后的文本
 */
export function preprocessText(text: string): string {
  if (!text) return '';

  let processed = text
    // 去除HTML标签（若输入含富文本）
    .replace(/<[^>]*>?/gm, '')
    // 替换URL为占位符（避免不同URL干扰语义）
    .replace(/https?:\/\/\S+/g, '[URL]')
    // 替换邮箱为占位符
    .replace(/\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}\b/g, '[EMAIL]')
    // 统一为小写
    .toLowerCase()
    // 去除多余标点（保留基本标点）
    .replace(/[^\w\s,.!?]/g, ' ')
    // 合并连续空格
    .replace(/\s+/g, ' ')
    // 去除首尾空格
    .trim();

  return processed;
}

// 测试预处理效果
console.log(preprocessText('Hello!  This is a Test. Visit https://example.com'));
// 输出："hello! this is a test. visit [url]"
