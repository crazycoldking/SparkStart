using System;

namespace GetWebData
{
    /// <summary>
    /// 自定义异常类
    /// </summary>
    public class HttpRequestExceptio : Exception
    {
        public HttpRequestExceptio()
        {
            
        }

        public HttpRequestExceptio(string message)
        {

        }

        public HttpRequestExceptio(string message, Exception ex)
        {

        }
        
    }
}