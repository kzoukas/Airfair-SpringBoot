
(function(){

    var topBarService = function($http){
        var getRedirectPage = function(){
            var repoUrl = "/redirect";
            return $http.get(repoUrl)
                .then(function(response){
                    return response.data;
                });
        };
        return {
            getRedirectPage: getRedirectPage

        };
    };
    var module = angular.module("flightTicketApp");
    module.factory("topBarService", topBarService);

}());
