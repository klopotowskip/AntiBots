<?php
$ini = getIni();$dbname = "`".str_replace("`","``",$ini['database-name'])."`";$tablename = "`".str_replace("`","``",$ini['table-name'])."`";
$sitekey = $ini['site-key'];$secretkey = $ini['secret-key'];
$header = $ini['header'];$title = $ini['title'];$placeholder = $ini['placeholder'];$submit = $ini['submit'];
$captchaerr = $ini['captcha-error'];$serverr = $ini['server-error'];$nickerr = $ini['nickname-error'];$inviperr = $ini['invalid-ip-error'];$charerr = $ini['illegal-chars-error'];$lengtherr = $ini['illegal-length-error'];
if(!isset($ini['wipe-password'])){  $i = 0;  $file = fopen('resources/verification.ini', 'a+');  $pswd = md5(getClientIp() . rand(0, 10000) . $inviperr);  fwrite($file, PHP_EOL);  fwrite($file, "wipe-password = \"$pswd\"");  fclose($file);}
$success = $ini['success'];if(!empty($ini['allowed_api_ips'])){  $allowedips = explode(' ', $ini['allowed_api_ips']);} else{  $allowedips = false;}
function getClientIP(){    $client  = @$_SERVER['HTTP_CLIENT_IP'];    $forward = @$_SERVER['HTTP_X_FORWARDED_FOR'];    $remote  = @$_SERVER['REMOTE_ADDR'];
    if(filter_var($client, FILTER_VALIDATE_IP)) return $client;    if(filter_var($forward, FILTER_VALIDATE_IP)) return $forward;    if(filter_var($remote, FILTER_VALIDATE_IP)) return $remote;    return '';}function getIni(){
  return parse_ini_file('verification.ini');}
function getConnection(){  $ini = getIni();  return new PDO("mysql:host=".$ini['host'], $ini['username'], $ini['password']);}
function successResponse($result){  return array(    'outcome' => true,    'result' => $result  );}
function failureResponse(){  return array(    'outcome' => false  );}function sendError($error){  $_SESSION['error'] = $error;  throw new Exception();}
function sendSuccess($success){  $_SESSION['success'] = $success;}