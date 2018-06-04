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
            $scope.flightList = data;
            $scope.typeOfFlight = model.params.typeOfFlight;
            $scope.totalItems = $scope.flightList.length;
            $scope.currentPage = 1;
            $scope.itemsPerPage=8;
            $scope.setPage = function (pageNo) {
                $scope.currentPage = pageNo;
            };
            $scope.maxSize = 3;

        };

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

        interval=setInterval(callService,15000);
        function callService(){
            flightInfoService.getFlightInfo(model.params.from, model.params.to, model.params.typeOfFlight)
                .then(onSuccess, onError);
            // if (severalTimes<7){
            //     flightListService.getFlightList(model.params.from, model.params.to, model.params.stops, model.params.maxPrice, model.params.flightDuration, model.params.connectingTime)
            //         .then(onSuccess, onError);
            //     severalTimes=severalTimes+1;
            // }
        }




        $scope.IsVisible = false;
        $scope.ShowHide = function(flight) {
            //If DIV is visible it will be hidden and vice versa.
            flight.IsVisible = !flight.IsVisible;
        }

        $scope.IsVisible1 = false;
        $scope.ShowHideReturn = function(flight1) {
            //If DIV is visible it will be hidden and vice versa.
            console.log("mpikaaa");
            flight1.IsVisible1 = !flight1.IsVisible1;
        }


    };


}());