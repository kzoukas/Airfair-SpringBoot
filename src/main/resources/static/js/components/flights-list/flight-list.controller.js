(function () {
    "use strict";

    var module = angular.module('flightTicketApp');
    module.component("flightList", {
        templateUrl: 'js/components/flights-list/flight-list.html',
        controllerAs: "model",
        controller: flightListController
    });
    flightListController.$inject=['$scope','$window', '$routeParams','$interval', 'flightListService','flightInfoService', 'parameterService'];
    function flightListController($scope,$window, $routeParams,$interval, flightListService, flightInfoService , parameterService){

        var model = this;
        model.$onInit=initialize;
        function initialize() {

        }
        var params = getSearchParameters();
        function getSearchParameters() {
            var prmstr = $window.location.search.substr(1);
            return prmstr != null && prmstr != "" ? transformToAssocArray(prmstr) : {};
        }

        function transformToAssocArray(prmstr) {
            var params = {};
            var prmarr = prmstr.split("&");
            for (var i = 0; i < prmarr.length; i++) {
                var tmparr = prmarr[i].split("=");
                params[tmparr[0]] = tmparr[1];
            }
            return params;
        }

        parameterService.setter(params);
        model.params=parameterService.getter();

        var onRepo = function(data){
            $scope.flightList = removeDuplicateUsingFilter(data);

            $scope.typeOfFlight = model.params.typeOfFlight;
            $scope.totalItems = $scope.flightList.length;
            $scope.currentPage = 1;
            $scope.itemsPerPage=8;
            $scope.setPage = function (pageNo) {
                $scope.currentPage = pageNo;
            };
            $scope.maxSize = 3;

        };
        function removeDuplicateUsingFilter(arr){
            var unique_array = arr.filter(function(elem, index, self) {
                return index == self.indexOf(elem);
            });
            return unique_array
        }

        var onError = function(reason){
            $scope.error = reason;
        };

        var onSuccess=function(data){
            $scope.flightSearched = data;
            if (!($scope.flightSearched)){

                flightListService.getFlightList(model.params.from,
                    model.params.to,
                    model.params.checkin,
                    model.params.checkout,
                    model.params.adultNum,
                    model.params.childNum,
                    model.params.typeOfFlight,
                    model.params.airportSize,
                    model.params.tripDistance,
                    model.params.stops,
                    model.params.maxPrice,
                    model.params.flightDuration,
                    model.params.connectingTimeStart,
                    model.params.connectingTimeEnd,
                    model.params.departureTimeStart,
                    model.params.departureTimeEnd,
                    model.params.arrivalTimeStart,
                    model.params.arrivalTimeEnd)
                    .then(onRepo, onError);

            }else{
                flightListService.getFlightList(model.params.from,
                    model.params.to,
                    model.params.checkin,
                    model.params.checkout,
                    model.params.adultNum,
                    model.params.childNum,
                    model.params.typeOfFlight,
                    model.params.airportSize,
                    model.params.tripDistance,
                    model.params.stops,
                    model.params.maxPrice,
                    model.params.flightDuration,
                    model.params.connectingTimeStart,
                    model.params.connectingTimeEnd,
                    model.params.departureTimeStart,
                    model.params.departureTimeEnd,
                    model.params.arrivalTimeStart,
                    model.params.arrivalTimeEnd)
                    .then(onRepo, onError);
                clearInterval(interval);
            }
        }


        flightListService.getFlightList(model.params.from,
            model.params.to,
            model.params.checkin,
            model.params.checkout,
            model.params.adultNum,
            model.params.childNum,
            model.params.typeOfFlight,
            model.params.airportSize,
            model.params.tripDistance,
            model.params.stops,
            model.params.maxPrice,
            model.params.flightDuration,
            model.params.connectingTimeStart,
            model.params.connectingTimeEnd,
            model.params.departureTimeStart,
            model.params.departureTimeEnd,
            model.params.arrivalTimeStart,
            model.params.arrivalTimeEnd)
            .then(onRepo, onError);

        callService();
        var interval = null;
        interval=setInterval(callService,8000);
        function callService(){
            flightInfoService.getFlightInfo(model.params.from,
                model.params.to,
                model.params.checkin,
                model.params.checkout,
                model.params.adultNum,
                model.params.childNum,
                model.params.typeOfFlight,
                model.params.airportSize,
                model.params.tripDistance)
                .then(onSuccess, onError);
        }

        $scope.dhowHide = function(flight) {
            //If DIV is visible it will be hidden and vice versa.
            flight.IsVisible = !flight.IsVisible;
        }
    };
}());
