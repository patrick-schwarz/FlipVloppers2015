<?php
  $dbHost = "134.0.27.180";
  $dbUser = "swe_user";
  $dbPass = "user";
  $dbName = "swe";
  $connect = @mysql_connect($dbHost, $dbUser, $dbPass) or die("<p class='error'>ERROR: Unable to connect to database server!</p>");
  $selectDB = @mysql_select_db($dbName, $connect) or die("<p class='error'>ERROR: Unable to select $dbName!</p>");
?>

