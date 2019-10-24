
 <?php
$koneksi = mysql_connect("localhost","root","");
if(!$koneksi){
   echo "Gagal";
}
$db = mysql_select_db("randis_pusinfolahta");
?> 