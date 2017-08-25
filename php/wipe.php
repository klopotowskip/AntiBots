<?php
if($_SERVER['REQUEST_METHOD'] != 'POST'){
  header("HTTP/1.1 400 Bad Request");
  die("400 – Bad Request");
}
session_start();
if(!isset($_POST['key'])){
  header("HTTP/1.1 400 Bad Request");
  die("400 – Bad Request");
}
$reqkey = $_POST['key'];
$reqkey = strtolower($reqkey);
$ini = parse_ini_file('resources/verification.ini');
$realkey = @$ini['wipe-password'];
if($reqkey != $realkey){
  header("HTTP/1.1 403 Forbidden");
  die("403 – Forbidden");
}
try{
  require_once 'resources/Utils.php';
  $pdo = getConnection();
  $pdo->query("use $dbname");
  $sql = "TRUNCATE TABLE $tablename";
  $result = $pdo->query($sql);
  session_destroy();
  die('Table truncated');
} catch(Exception $e){
  header("HTTP/1.1 500 Internal Server Error");
  session_destroy();
  die("500 – Internal Server Error");
}
 ?>
