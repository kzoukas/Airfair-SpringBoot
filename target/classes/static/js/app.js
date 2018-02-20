(function(){

    var app = angular.module("flightTicketApp", ["ngRoute"]);

    app.config(function($routeProvider,$locationProvider){
        $locationProvider.html5Mode(true).hashPrefix('!');
        $routeProvider
            .when("/allflights", {
                template: "<flight-list></flight-list>",

            })
            .when("/", {
                templateUrl: "js/components/thesis-ticket/welcome.html",

        })
            // .otherwise({redirectTo:"/"});
    });

    app.run(['$window', 'parameterService', function ($window, parameterService) {

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

    }]);


}());
