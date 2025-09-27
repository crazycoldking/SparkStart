import { pipeline } from '@xenova/transformers';
import { OpenAI } from 'openai';

// 本地模型（Sentence-BERT）
export class LocalEmbedder {
  private static instance: LocalEmbedder;
  private model: any;

  // 单例模式避免重复加载模型
  private constructor() {}

  public static async getInstance() {
    if (!LocalEmbedder.instance) {
      LocalEmbedder.instance = new LocalEmbedder();
      // 加载专为句向量优化的模型（而非通用特征提取模型）
      LocalEmbedder.instance.model = await pipeline(
        'feature-extraction', 
        'Xenova/all-MiniLM-L6-v2'  // 推荐模型：语义保真度高，384维向量
      );
    }
    return LocalEmbedder.instance;
  }

  // 生成向量
  async embed(text: string): Promise<number[]> {
    const result = await this.model(text, { 
      pooling: 'mean',  // 句向量生成关键：对词向量取平均
      normalize: true   // 输出前归一化（确保余弦相似度计算准确）
    });
    return Array.from(result.data) as number[];
  }
}

// OpenAI 嵌入模型（更高精度，需API）
export class OpenAIEmbedder {
  private openai: OpenAI;

  constructor(apiKey: string) {
    this.openai = new OpenAI({ apiKey });
  }

  async embed(text: string): Promise<number[]> {
    const response = await this.openai.embeddings.create({
      model: 'text-embedding-ada-002',  // 推荐模型：语义捕捉能力强
      input: text
    });
    return response.data[0].embedding;
  }
}
