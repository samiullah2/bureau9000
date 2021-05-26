<?php
class KastRisico{
  
    // database connection and table name
    private $conn;
    private $table_name = "kastRisico";
  
    // object properties
    public $id;
    public $beschrijving;
    public $w;
    
  
    // constructor with $db as database connection
    public function __construct($db){
        $this->conn = $db;
    }

    // read kastRisico
function read(){

  
    //select all query
       $query = 
       "SELECT id, beschrijving, w
        FROM kastRisico ";

    // prepare query statement
    $stmt = $this->conn->prepare($query);
  
    // execute query
    $stmt->execute();
  
    return $stmt;
    }

    
    // used when you want a specific kastRisico
    function readOne(){
  
    // query to read single record
    $query = "SELECT id, beschrijving, w
              FROM kastRisico 
              WHERE id = ?
              LIMIT 0,1";
  
    // prepare query statement
    $stmt = $this->conn->prepare( $query );
  
    // bind id of kastRisico
    $stmt->bindParam(1, $this->id);
  
    // execute query
    $stmt->execute();
  
    // get retrieved row
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
  
    // set values to object properties
    $this->beschrijving = $row['beschrijving'];
    $this->w = $row['w'];
    }

    


}
?>