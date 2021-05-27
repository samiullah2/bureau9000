<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
  
// database connection will be here

// include database and object files
require '../config/database.php';
require '../objects/gebouwRisico.php';
  
// instantiate database and gebouwRisico object
$database = new Database();
$db = $database->getConnection();
  
// initialize object
$gebouwRisico = new GebouwRisico($db);

// query gebouwRisicos
$stmt = $gebouwRisico->read();
$num = $stmt->rowCount();
  
// check if more than 0 record found
if($num>0){
  
    // gebouwRisicos array
    $gebouwRisicos_arr=array();
  
    // retrieve our table contents
    // fetch() is faster than fetchAll()
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        // extract row
        // this will make $row['name'] to
        // just $name only
        extract($row);
  
        $gebouwRisico_item=array(
            "id" => $id,
            "beschrijving" => $beschrijving,
            "w" => $w
        );
  
        array_push($gebouwRisicos_arr, $gebouwRisico_item);
    }
  
    // set response code - 200 OK
    http_response_code(200);
  
    // show gebouwRisicos data in json format
    echo json_encode($gebouwRisicos_arr);
}
  
// no gebouwRisicos found will be here

else{
  
  // set response code - 404 Not found
  http_response_code(404);

  // tell the user no gebouwRisicos found
  echo json_encode(
      array("message" => "No gebouwRisicos found.")
  );
}
