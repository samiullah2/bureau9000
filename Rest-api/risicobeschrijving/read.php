<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
  
// database connection will be here

// include database and object files
require '../config/database.php';
require '../objects/risicobeschrijving.php';
  
// instantiate database and risicobeschrijving object
$database = new Database();
$db = $database->getConnection();
  
// initialize object
$risicobeschrijving = new risicobeschrijving($db);

// query risicobeschrijvings
$stmt = $risicobeschrijving->read();
$num = $stmt->rowCount();
  
// check if more than 0 record found
if($num>0){
  
    // risicobeschrijvings array
    $risicobeschrijvings_arr=array();
  
    // retrieve our table contents
    // fetch() is faster than fetchAll()
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        // extract row
        // this will make $row['name'] to
        // just $name only
        extract($row);
  
        $risicobeschrijving_item=array(
            "id" => $id,
            "beschrijving" => $beschrijving,
            "waarde" => $waarde
        );
  
        array_push($risicobeschrijvings_arr, $risicobeschrijving_item);
    }
  
    // set response code - 200 OK
    http_response_code(200);
  
    // show risicobeschrijvings data in json format
    echo json_encode($risicobeschrijvings_arr);
}
  
// no risicobeschrijvings found will be here

else{
  
  // set response code - 404 Not found
  http_response_code(404);

  // tell the user no risicobeschrijvings found
  echo json_encode(
      array("message" => "No risicobeschrijvings found.")
  );
}
