<?php
function	escape_it(&$var)
{
  if ($var != "")
    $var = mysql_escape_string($var);
}

function	parse_this_title($title, &$start, &$end, &$by)
{
  list($start, $end) = explode(" vers ", $title);
  $start = trim(substr($start, 2));
  list($end, $by) =  explode(" par ", $end);
  $end = trim($end);
  $by = trim(substr($by, 0, -32));
  escape_it($start);
  escape_it($end);
  escape_it($by);
}

function	add_in_stations(&$array, $var)
{
  $r1 = "/Métro-Connexion/i";
  $r2 = "/gne /";
  if (!in_array($var, $array) && $var != "" && !preg_match($r1, $var) && !preg_match($r2, $var))
    {
      $id = insert_new_station($var);
      $array[$id] = $var;
    }
}

function	find_id($array, $val)
{
  foreach ($array as $k => $v)
    {
      if ($v == $val)
	return $k;
    }
  return 0;
}
?>