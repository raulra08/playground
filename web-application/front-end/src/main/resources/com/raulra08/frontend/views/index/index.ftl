<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
    <meta http-equiv="Content-Type"
          content="text/html;charset=utf-8"/>
    <title>Front End</title>
</head>

<body ng-controller="TextController">
<p>{{someText.message}}</p>

<form ng-controller="TextController">
    Starting: <input ng-change="computeNeeded()"
                     ng-model="funding.startingEstimate">
    Recommendation: {{funding.needed}}
</form>

<script
        src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js">
</script>

<script>
  var myAppModule = angular.module('myApp', []);

  myAppModule.controller('TextController',
    function($scope) {
        var someText = {};
        someText.message = 'You have started your journey.';
        $scope.someText = someText;

        $scope.funding = { startingEstimate: 0 };
        $scope.computeNeeded = function() {
            $scope.funding.needed = $scope.funding.startingEstimate * 10;
        };
  });
</script>
</body>
</html>