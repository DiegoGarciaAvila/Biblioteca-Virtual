<?php 

$mail = $_POST['email'];

$header = 'from: '.$mail."\r\n";
$header .= "X-Mailer:PHP/".phpversion()."\r\n";
$header .= "Mime-Version:1.0 \r\n";
$header .= "content-Type:text/plain";

$message = "este message fue enviado por: ".$mail."\r\n";
$message .= "asunto: ".$asunto."\r\n";
$message .= "mensaje: ".$_POST['mensaje']."\r\n";
$message .= "enviado el: ".date('d/m/Y',time());


$para = 'algonewsss@yahoo.com';
$asunto = 'mensaje despedida';

if(mail($para,$asunto,utf8_decode($message),$header))
echo "<script type='text/javascript'>alert('Tu message ha sido enviado exitosamente');</script>";
echo "<script type='text/javascript'>window.location.href='http://servitecflhuaraz.com';</script>";

 ?>