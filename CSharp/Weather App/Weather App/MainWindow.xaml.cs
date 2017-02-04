using System.Windows;
using System.Windows.Input;
using Newtonsoft.Json;

namespace Weather_App
{
    public partial class MainWindow
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        public static WeatherMessage GetWeather(string jsonResult)
        {
            //Using JsonConvert class to deserialize json string into object
            WeatherMessage weatherMessage = JsonConvert.DeserializeObject<WeatherMessage>(jsonResult);
            return weatherMessage;
        }

        private void BtnGetWeatherData_Click(object sender, RoutedEventArgs e)
        {
            GetWeatherDetail();
        }

        private void GetWeatherDetail()
        {
            var location = TbCity.Text;
            var baiduAk = "6tYzTvGZSOpYB5Oc2YGGOKt8&location";
            var query = "http://api.map.baidu.com/telematics/v3/weather?output=json&ak=" + baiduAk + "=" + location;  
            //send http request and get query result with json/xml format string
            var jsonResult = GetWebData.GetDataWithUrl.SendRequestByGetMethod(query, null);
            //var result = GetWebData.GetDataWithUrl.SendRequestByPostMethod("http://www.baidu.com", "para", null);
            var result = GetWeather(jsonResult);

            string res = string.Empty;
            if (result.Status.ToLower() != "success")
            {
                res = "Failed to get weather condition！";
            }
            else
            {
                foreach (var resultResult in result.Results)
                {
                    res += "Current City: " + resultResult.CurrentCity + "\r\n";
                    res += "pm2.5: " + resultResult.Pm25 + "\r\n";
                    res += "\r\nDetail Report：\r\n";
                    if (GetDetailWeatherReport(resultResult, ref res)) continue;

                    res += "\r\nWeather Index：\r\n";
                    res = GetWeatherIndex(resultResult, res);
                }
            }
            TbWeatherDetail.Text = res;
        }

        private static bool GetDetailWeatherReport(WeatherModel resultResult, ref string res)
        {
            if (resultResult.weather_data == null) return true;
            foreach (var item in resultResult.weather_data)
            {
                res += item.Date + "\r\n";
                res += item.Temperature + "\r\n";
                res += item.Wind + "\r\n";
                res += "-----------------------------\r\n";
            }
            return false;
        }

        private static string GetWeatherIndex(WeatherModel resultResult, string res)
        {
            if (resultResult.index == null) return res;
            foreach (var item in resultResult.index)
            {
                res += item.Tipt + "： ";
                res += item.Zs + "\r\n";
                res += item.Des + "\r\n";
                res += "-----------------------------\r\n";
            }
            return res;
        }

        private void TbWeatherDetail_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.Key == Key.Enter)
            {
                GetWeatherDetail();
            }
        }  
        
    }
}
