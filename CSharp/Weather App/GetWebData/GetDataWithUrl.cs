using System;
using System.IO;
using System.Net;
using System.Net.Security;
using System.Security.Cryptography.X509Certificates;
using System.Text;

namespace GetWebData
{
    public class GetDataWithUrl
    {
        /// <summary>
        /// Get方式获取url地址输出内容
        /// </summary>
        /// <param name="url">url</param>
        /// <param name="encoding">返回内容编码方式，例如：Encoding.UTF8</param>
        /// <returns></returns>
        public static string SendRequestByGetMethod(string url, Encoding encoding)
        {
            try
            {
                var webRequest = (HttpWebRequest)WebRequest.Create(url);
                webRequest.Method = "GET";
                var webResponse = (HttpWebResponse)webRequest.GetResponse();
                if (encoding == null)
                {
                    encoding = Encoding.UTF8;
                }
                var data = webResponse.GetResponseStream();
                if (data == null)
                {
                    return string.Empty;
                }
                using (var sr = new StreamReader(data, encoding))
                {
                    return sr.ReadToEnd();
                }
            }
            catch (Exception)
            {
                throw new HttpRequestExceptio();
            }
        }

        /// <summary>
        /// 采用https协议访问网络
        /// </summary>
        /// <param name="url">url地址</param>
        /// <param name="postData">发送的数据</param>
        /// <param name="encoding">编码格式</param>
        /// <returns>返回执行结果</returns>
        public static string SendRequestByPostMethod(string url, string postData, Encoding encoding)
        {
            try
            {
                var webRequest = (HttpWebRequest) WebRequest.Create(url);
                webRequest.Method = "POST";
                webRequest.Accept = "text/html, application/xhtml+xml, */*";
                webRequest.ContentType = "application/x-www-form-urlencoded";
                if (encoding == null)
                {
                    encoding = Encoding.UTF8;
                }
                byte[] buffer = encoding.GetBytes(postData);
                webRequest.ContentLength = buffer.Length;
                webRequest.GetRequestStream().Write(buffer, 0, buffer.Length);


                var webResponse = (HttpWebResponse) webRequest.GetResponse();

                var reader = webResponse.GetResponseStream();
                if (reader == null)
                {
                    return string.Empty;
                }
                using (var sr = new StreamReader(reader, encoding))
                {
                    return sr.ReadToEnd();
                }
            }
            catch (Exception)
            {
                throw new HttpRequestExceptio();
            }

        }

        /// <summary>
        /// 证书验证
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="certificate"></param>
        /// <param name="chain"></param>
        /// <param name="errors"></param>
        /// <returns></returns>
        public bool CheckValidationResult(object sender, X509Certificate certificate, X509Chain chain, SslPolicyErrors errors)
        {
            // 总是接受    
            return true;
        }

        public string GetUrltoHtml(string url)
        {
            StringBuilder content = new StringBuilder();

            try
            {
                // 这一句一定要写在创建连接的前面。使用回调的方法进行证书验证。
                ServicePointManager.ServerCertificateValidationCallback = CheckValidationResult;

                // 与指定URL创建HTTP请求
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);

                // 创建证书文件
                X509Certificate objx509 = new X509Certificate("\\123.cer");

                // 添加到请求里
                request.ClientCertificates.Add(objx509);

                // 获取对应HTTP请求的响应
                HttpWebResponse response = (HttpWebResponse)request.GetResponse();
                // 获取响应流
                Stream responseStream = response.GetResponseStream();
                // 对接响应流(以"GBK"字符集)
                StreamReader sReader = new StreamReader(responseStream, Encoding.GetEncoding("utf-8"));
                // 开始读取数据
                Char[] sReaderBuffer = new Char[256];
                int count = sReader.Read(sReaderBuffer, 0, 256);
                while (count > 0)
                {
                    String tempStr = new String(sReaderBuffer, 0, count);
                    content.Append(tempStr);
                    count = sReader.Read(sReaderBuffer, 0, 256);
                }
                // 读取结束
                sReader.Close();
            }
            catch (Exception)
            {
                content = new StringBuilder("Runtime Error");
            }

            return content.ToString();
        }

        public string OpenReadWithHttps(string url, string strPostdata, string strEncoding)
        {
            // 这一句一定要写在创建连接的前面。使用回调的方法进行证书验证。
            ServicePointManager.ServerCertificateValidationCallback = CheckValidationResult;
            Encoding encoding = Encoding.Default;
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);

            //创建证书文件
            X509Certificate objx509 = new X509Certificate("\\123.cer");

            //加载Cookie
            request.CookieContainer = new CookieContainer();

            //添加到请求里
            request.ClientCertificates.Add(objx509);
            request.Method = "post";
            request.Accept = "text/html, application/xhtml+xml, */*";
            request.ContentType = "application/x-www-form-urlencoded";
            byte[] buffer = encoding.GetBytes(strPostdata);
            request.ContentLength = buffer.Length;
            request.GetRequestStream().Write(buffer, 0, buffer.Length);
            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            using (StreamReader reader = new StreamReader(response.GetResponseStream(), Encoding.GetEncoding(strEncoding)))
            {
                return reader.ReadToEnd();
            }
        }
    }
}
