<?php 
 
	//Import File Koneksi Database
	require_once('koneksi.php');
	
	//Membuat SQL Query
	$sql = "SELECT * FROM randis;";
	
	//Mendapatkan Hasil
	$tabel = mysql_query($sql,$koneksi);
	
	
	//Membuat Array Kosong 
	$result = array();
	
	while($row = mysql_fetch_array($tabel)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
		"NO"=>$row['NO'],
		"JABATAN"=>$row['JABATAN'],
		"NO_PLAT"=>$row['NO_PLAT'],
		"NO_RANGKA"=>$row['NO_RANGKA'],
		"NO_MESIN"=>$row['NO_MESIN'],
		"JENIS"=>$row['JENIS'],
		"TYPE"=>$row['TYPE'],
		"MERK"=>$row['MERK'],
		"SILINDER"=>$row['SILINDER'],
		"TAHUN"=>$row['TAHUN'],

		));
		
	}
	
	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));
	
	mysql_close($koneksi);
?>