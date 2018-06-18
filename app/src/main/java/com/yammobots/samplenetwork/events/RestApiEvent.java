package com.yammobots.samplenetwork.events;

import com.yammobots.samplenetwork.data.vo.CountryVO;

import java.util.List;

/**
 * Created by yepyaesonetun on 6/18/18.
 **/

public class RestApiEvent {

    public static class EmptyResponseEvent{
        private String message;

        public EmptyResponseEvent(String message) {
            this.message = message;
        }

        public String getEmptyResponseMsg(){
            return message;
        }
    }

    public static class ErrorInvokingAPIEvent {
        private String errorMessage;

        public ErrorInvokingAPIEvent(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage(){
            return errorMessage;
        }
    }

    public static class CounrtyDataLoadedEvent{
        private List<CountryVO> countryVOList;

        public CounrtyDataLoadedEvent(List<CountryVO> countryVOList) {
            this.countryVOList = countryVOList;
        }

        public List<CountryVO> getCountryVOList(){
            return countryVOList;
        }
    }
}
