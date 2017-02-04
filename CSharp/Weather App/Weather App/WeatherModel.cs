using System;
using System.Runtime.Serialization;

namespace Weather_App
{
    public class WeatherModel  
    {  
        [DataMember]  
        public String CurrentCity{ get; set; }  

        [DataMember]  
        public String Pm25 { get; set;}

        [DataMember]
        public WeatherIndex[] index { get; set; }  

        [DataMember]  
        public WeatherDetail[] weather_data { get; set; }  
    }

    public class WeatherIndex
    {
        [DataMember]
        public string Title{ get; set; }

        [DataMember]
        public string Zs { get; set; }

        [DataMember]
        public string Tipt { get; set; }

        [DataMember]
        public string Des { get; set; }
    }
}