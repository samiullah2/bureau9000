<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
  
// database connection will be here

// include database and object files
require '../config/database.php';
require '../objects/project.php';
  
// instantiate database and project object
$database = new Database();
$db = $database->getConnection();
  
// initialize object
$project = new Project($db);

// query projects
$stmt = $project->read();
$num = $stmt->rowCount();
  
// check if more than 0 record found
if($num>0){
  
    // projects array
    $projects_arr=array();
  
    // retrieve our table contents
    // fetch() is faster than fetchAll()
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        // extract row
        // this will make $row['name'] to
        // just $name only
        extract($row);
  
        $project_item=array(
            "id" => $id,
            "naam" => $naam
        );
  
        array_push($projects_arr, $project_item);
    }
  
    // set response code - 200 OK
    http_response_code(200);
  
    // show projects data in json format
    echo json_encode($projects_arr);
}
  
// no projects found will be here

else{
  
  // set response code - 404 Not found
  http_response_code(404);

  // tell the user no projects found
  echo json_encode(
      array("message" => "No projects found.")
  );
}
