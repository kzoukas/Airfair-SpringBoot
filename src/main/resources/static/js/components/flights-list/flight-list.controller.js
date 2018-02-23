(function () {
    "use strict";

    var module = angular.module("flightTicketApp");
    module.component("flightList", {
        templateUrl: 'js/components/flights-list/flight-list.html',
        controllerAs: "model",
        controller: flightListController
    });
    flightListController.$inject=['$scope','$window', '$routeParams','$interval', 'flightListService', 'parameterService'];
    function flightListController($scope,$window, $routeParams,$interval, flightListService, parameterService){

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
            for(var i=0;i<data.length;i++) {
                if (data[i].fromCompany == "Olympic Air") {

                    var companyImage = {
                        flag: "./images/olympicAir.jpg",
                        alt: "OlympicAir"
                    };
                    $scope.flightList[i].companyImage = companyImage;

                }else if(data[i].fromCompany == "Aegean Airlines"){
                    var companyImage = {
                        flag: "./images/aegean.jpg",
                        alt: "Aegean"
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }else if(data[i].fromCompany == "Turkish Airlines"){
                    var companyImage = {
                        flag: "./images/turkish.jpg",
                        alt: "Turkish"
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }
                else if(data[i].fromCompany == "Bulgaria Air"){
                    var companyImage = {
                        flag: "./images/bulgariaAir.jpg",
                        alt: "Bulgaria air"
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }else if(data[i].fromCompany == "Alitalia"){
                    var companyImage = {
                        flag: "./images/alitalia.jpg",
                        alt: "Alitalia"
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }else if(data[i].fromCompany == "Ellinair"){
                    var companyImage = {
                        flag: "./images/ellinair.jpg",
                        alt: "Ellinair"
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }else if(data[i].fromCompany == "Ryanair"){
                    var companyImage = {
                        flag: "./images/ryanair.jpg",
                        alt: "Ryanair"
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }else if(data[i].fromCompany == "Montenegro Airlines"){
                    var companyImage = {
                        flag: "./images/montenegro.jpg",
                        alt: "Montenegro"
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }else if(data[i].fromCompany == "Tarom Romanian Air"){
                    var companyImage = {
                        flag: "./images/tarom.jpg",
                        alt: "Tarom"
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }else if(data[i].fromCompany == "Adria Airways"){
                    var companyImage = {
                        flag: "./images/adria.jpg",
                        alt: "Adria"
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }else if(data[i].fromCompany == "Wizzair"){
                    var companyImage = {
                        flag: "./images/wizzair.jpg",
                        alt: "Wizzair"
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }else if(data[i].fromCompany == "Swiss International Airlines"){
                    var companyImage = {
                        flag: "./images/swiss.jpg",
                        alt: "Swiss International Airlines"
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }else if(data[i].fromCompany == "Pegasus Airlines"){
                    var companyImage = {
                        flag: "./images/pegasus.jpg",
                        alt: "Pegasus Airlines"
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }else if(data[i].fromCompany == "Air Serbia"){
                    var companyImage = {
                        flag: "./images/airSerbia.jpg",
                        alt: "Air Serbia"
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }else if(data[i].fromCompany == "Air France"){
                    var companyImage = {
                        flag: "./images/airFrance.jpg",
                        alt: "Air France"
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }else{
                    var companyImage = {
                        flag: "",
                        alt: data[i].fromCompany
                    };
                    $scope.flightList[i].companyImage = companyImage;
                }
            }
        };
        var onError = function(reason){
            $scope.error = reason;
        };
        callService();
        // $interval(callService,10000);
        function callService(){
            flightListService.getFlightList(model.params.from,model.params.to,model.params.stops,model.params.maxPrice,model.params.flightDuration)
                .then(onRepo, onError);
        }

    };

}());