(function(){

    var app = angular.module("flightTicketApp", ["ngRoute"]);

    app.config(function($routeProvider){
        $routeProvider
            .when("/allflights", {
                templateUrl: "js/components/thesis-ticket/flightResults.html",
                controller: "flightController"
            }).when("/", {
            templateUrl: "js/components/thesis-ticket/welcome.html"

        })
            .otherwise({redirectTo:"/"});
    });
}());
