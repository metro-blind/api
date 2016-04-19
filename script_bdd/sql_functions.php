<?php
function	insert_new_station($station)
{
  $sql = "INSERT INTO `station` (`name`) VALUE ('" . $station . "');";
  MyPDO::executeQuery($sql);
  $sql = "SELECT `id` FROM `station` WHERE `name` LIKE '" . $station . "';" ;
  $res = MyPDO::executeQuery($sql);
  return (int) $res[0]['id'];
}

function	insert_itinerary($id_start, $id_end, $text)
{
  $sql = "INSERT INTO `itinerary` (text, station_id_begin, station_id_end) VALUE ";
  $sql .= " ('" . $text . "', " . $id_start . ", " . $id_end . ");";
  MyPDO::executeQuery($sql);
}
?>