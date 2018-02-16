// (function() {
//     var module = angular.module("flightTicketApp");
//     var filterController = function($scope, filterService) {
//
//         var onRepo2 = function(data){
//             $scope.flightList = data;
//         };
//         var onError2 = function(reason){
//             $scope.error = reason;
//         };
//
//
//         $scope.filterUse=function()
//         {
//
//             filterService.getFilters().then(onRepo2, onError2);
//
//         }
//
//     };
//     module.controller("filterController", filterController);
// }());