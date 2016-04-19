#!/usr/bin/php
<?php
include('simple_html_dom.php');
include('Config.php');
include('MyPDO.php');
include('functions.php');
include('sql_functions.php');
$html_src = "./src";
$files = scandir($html_src);
$i = $j = 0;
$stations = array();
foreach ($files as $file)
  {
    $i++;
  }
foreach ($files as $file)
  {
    $j++;
    if ($file != '.' && $file != '..' && substr($file, -4) == "html")
      {
	$text = $start = $end = $by = "";
	$html = file_get_html($html_src . "/" . $file);
	foreach ($html->find('title') as $element)
	  {
	    parse_this_title($element->innertext, $start, $end, $by);
	    add_in_stations($stations, $start);
	    add_in_stations($stations, $end);
	  }
	$id_start = find_id($stations, $start);
	$id_end = find_id($stations, $end);
	if ($id_start > 0 && $id_end > 0 && $id_start != $id_end)
	  {
	    echo $j * 100 / $i . "%\n";
	    foreach ($html->find('.contenu') as $element)
	      $text .= $element->innertext . "<br>";
	    $text = mysql_escape_string($text);
	    insert_itinerary($id_start, $id_end, $text);
	  }
      }
  }
?>