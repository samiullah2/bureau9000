<?php
class GebouwRisico{
  
    // database connection and table name
    private $conn;
    private $table_name = "gebouwRisico";
  
    // object properties
    public $id;
    public $beschrijving;
    public $w;
    
  
    // constructor with $db as database connection
    public function __construct($db){
        $this->conn = $db;
    }

    // read gebouwRisicos
    function read(){

    //select all query
       $query = 
       "SELECT id, beschrijving, w
        FROM gebouwRisico ";

    // prepare query statement
    $stmt = $this->conn->prepare($query);
  
    // execute query
    $stmt->execute();
  
    return $stmt;
    }

    
    // used when you want a specific gebouwRisico
    function readOne(){
  
    // query to read single record
    $query = "SELECT id, beschrijving, w
              FROM gebouwRisico 
              WHERE id = ?
              LIMIT 0,1";
  
    // prepare query statement
    $stmt = $this->conn->prepare( $query );
  
    // bind id of gebouwRisico
    $stmt->bindParam(1, $this->id);
  
    // execute query
    $stmt->execute();
  
    // get retrieved row
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
  
    // set values to object properties
    $this->beschrijving = $row['beschrijving'];
    $this->w = $row['w'];
    }


    // used when you want to search for a specific gebouwRisico based on his name
    function readOneName(){
  
        // query to read single record
        $query = "SELECT id, beschrijving, w
                  FROM gebouwRisico 
                  WHERE beschrijving = ?
                  LIMIT 0,1";
      
        // prepare query statement
        $stmt = $this->conn->prepare( $query );
      
        // bind id of gebouwRisico
        $stmt->bindParam(1, $this->beschrijving);
      
        // execute query
        $stmt->execute();
      
        // get retrieved row
        $row = $stmt->fetch(PDO::FETCH_ASSOC);
      
        // set values to object properties
        $this->id = $row['id'];
        }

    // create gebouwRisico
    function create(){
  
    // query to insert record
    $query = "INSERT INTO gebouwRisico
                SET beschrijving=:beschrijving, w=:w";
  
    // prepare query
    $stmt = $this->conn->prepare($query);
  
    // sanitize
    $this->beschrijving=htmlspecialchars(strip_tags($this->beschrijving));
    $this->w=htmlspecialchars(strip_tags($this->w));
  
    // bind values
    $stmt->bindParam(":beschrijving", $this->beschrijving);
    $stmt->bindParam(":w", $this->w);
  
    // execute query
    if($stmt->execute()){
        return true;
    }
  
    return false;
    }
}
?>