<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
  
// database connection will be here

// include database and object files
require '../config/database.php';
require '../objects/kastRisico.php';
  
// instantiate database and kastRisico object
$database = new Database();
$db = $database->getConnection();
  
// initialize object
$kastRisico = new KastRisico($db);

// query kastRisicos
$stmt = $kastRisico->read();
$num = $stmt->rowCount();
  
// check if more than 0 record found
if($num>0){
  
    // kastRisicos array
    $kastRisicos_arr=array();
  
    // retrieve our table contents
    // fetch() is faster than fetchAll()
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        // extract row
        // this will make $row['name'] to
        // just $name only
        extract($row);
  
        $kastRisico_item=array(
            "id" => $id,
            "beschrijving" => $beschrijving,
            "w" => $w
        );
  
        array_push($kastRisicos_arr, $kastRisico_item);
    }
  
    // set response code - 200 OK
    http_response_code(200);
  
    // show kastRisicos data in json format
    echo json_encode($kastRisicos_arr);
}
  
// no kastRisicos found will be here

else{
  
  // set response code - 404 Not found
  http_response_code(404);

  // tell the user no kastRisicos found
  echo json_encode(
      array("message" => "No kastRisicos found.")
  );
}
