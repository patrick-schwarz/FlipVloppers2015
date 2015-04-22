<?php 

function authenticate($username, $password) 
{ 
  //require("connect.php");
  $query = "SELECT * FROM user WHERE username=\"$username\"";
  
  $result = MYSQL_QUERY($query);
  $number = MYSQL_NUMROWS($result);
  
  if ($number != 0)
  {
	$salt = mysql_result($result, 0, "salt");
	$hashpw = hash('md5', $password . $salt);
	$id = mysql_result($result, 0, "iduser");
    
	if (!strcmp(mysql_result($result, 0, "password"), $hashpw))
	{
	//  require("disconnect.php");
	  return $id;
	}
  }
   // require("disconnect.php");
    return -1; 
} 
?>