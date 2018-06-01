(function () {
    "use strict";

    var module = angular.module("flightTicketApp");
    module.component("sideBar", {
        templateUrl: 'js/components/side-bar/sidebar.html',
        controllerAs: "model",
        controller: sideBarController
    });
    sideBarController.$inject=['$scope','$location','parameterService','$route'];
    function sideBarController($scope,$location,parameterService,$route){
        var model = this;
        model.stops=1;
        model.maxPriceSlider = {
            value: 1200,
                options: {
                floor: 0,
                ceil: 2000,
            }
        }
        model.flightDurationSlider = {
            value: 15,
                options: {
                floor: 0,
                ceil: 30,
            }
        }
        model.connectingTimeSlider = {
            minValue: 1,
            maxValue: 24,
                options: {
                floor: 1,
                    ceil: 24,
            }
        }
        model.departureTimeSlider = {
            minValue: 0,
            maxValue: 24,
            options: {
                floor: 0,
                ceil: 24,
            }
        }
        model.arrivalTimeSlider = {
            minValue: 0,
            maxValue: 24,
            options: {
                floor: 0,
                ceil: 24,
            }
        }
        $scope.filter = {
            stops:"1",
            maxPriceSlider : {
                value: 1200,
                options: {
                    floor: 0,
                    ceil: 2000,
                }
            },
            flightDurationSlider : {
                value: 15,
                options: {
                    floor: 0,
                    ceil: 30,
                }
            },
            connectingTimeSlider : {
                minValue: 1,
                maxValue: 24,
                options: {
                    floor: 1,
                    ceil: 24,
                }
            },
            departureTimeSlider : {
                minValue: 0,
                maxValue: 24,
                options: {
                    floor: 0,
                    ceil: 24,
                }
            },
            arrivalTimeSlider : {
                minValue: 0,
                maxValue: 24,
                options: {
                    floor: 0,
                    ceil: 24,
                }
            }
        };
        $scope.submitFilters=function() {
            model.params = parameterService.getter();
            model.stops = $scope.filter.stops;
            model.maxPrice=$scope.filter.maxPriceSlider.value ;
            model.flightDuration=$scope.filter.flightDurationSlider.value;
            model.connectingTimeStart=$scope.filter.connectingTimeSlider.minValue;
            model.connectingTimeEnd=$scope.filter.connectingTimeSlider.maxValue;
            model.arrivalTimeStart=$scope.filter.arrivalTimeSlider.minValue;
            model.arrivalTimeEnd=$scope.filter.arrivalTimeSlider.maxValue;
            model.departureTimeStart=$scope.filter.departureTimeSlider.minValue;
            model.departureTimeEnd=$scope.filter.departureTimeSlider.maxValue;

            $location.path('/allflights').search({from:model.params.from,
                to:model.params.to,
                checkin:model.params.checkin,
                checkout:model.params.checkout,
                typeOfFlight:model.params.typeOfFlight,
                adultNum:model.params.adultNum,
                childNum:model.params.childNum,
                airportSize:model.params.airportSize,
                tripDistance:model.params.tripDistance,
                stops:model.stops,
                maxPrice:model.maxPrice,
                flightDuration:model.flightDuration,
                connectingTimeStart:model.connectingTimeStart,
                connectingTimeEnd:model.connectingTimeEnd,
                departureTimeStart:model.departureTimeStart,
                departureTimeEnd:model.departureTimeEnd,
                arrivalTimeStart:model.arrivalTimeStart,
                arrivalTimeEnd:model.arrivalTimeEnd});
        }
        model.params=parameterService.getter();

        $location.path('/allflights').search({from:model.params.from,
            to:model.params.to,
            checkin:model.params.checkin,
            checkout:model.params.checkout,
            typeOfFlight:model.params.typeOfFlight,
            adultNum:model.params.adultNum,
            childNum:model.params.childNum,
            airportSize:model.params.airportSize,
            tripDistance:model.params.tripDistance,
            stops:model.stops,
            maxPrice:model.maxPriceSlider.value,
            flightDuration:model.flightDurationSlider.value,
            connectingTimeStart:model.connectingTimeSlider.minValue,
            connectingTimeEnd:model.connectingTimeSlider.maxValue,
            departureTimeStart:model.departureTimeSlider.minValue,
            departureTimeEnd:model.departureTimeSlider.maxValue,
            arrivalTimeStart:model.arrivalTimeSlider.minValue,
            arrivalTimeEnd:model.arrivalTimeSlider.maxValue});};
}());