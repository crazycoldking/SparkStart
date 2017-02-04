using System;
using System.Runtime.Serialization;

namespace Weather_App
{

    public class WeatherMessage  
    {  
        [DataMember]  
        public String Error { get;set; }  

        [DataMember]  
        public String Status { get;set; }  

        [DataMember]  
        public String Date { get; set;}  

        [DataMember]  
        public WeatherModel[] Results{ get; set; }  
   
    }
}