<?php
class Project{
  
    // database connection and table name
    private $conn;
    private $table_name = "project";
  
    // object properties
    public $id;
    public $naam;
    
  
    // constructor with $db as database connection
    public function __construct($db){
        $this->conn = $db;
    }

    // read projecten
    function read(){

  
    //select all query
       $query = 
       "SELECT id, naam
        FROM project ";

    // prepare query statement
    $stmt = $this->conn->prepare($query);
  
    // execute query
    $stmt->execute();
  
    return $stmt;
    }

    
    // used when you want a specific project
    function readOne(){
  
    // query to read single record
    $query = "SELECT id, naam
              FROM project 
              WHERE id = ?
              LIMIT 0,1";
  
    // prepare query statement
    $stmt = $this->conn->prepare( $query );
  
    // bind id of project
    $stmt->bindParam(1, $this->id);
  
    // execute query
    $stmt->execute();
  
    // get retrieved row
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
  
    // set values to object properties
    $this->naam = $row['naam'];
    }


    // used when you want to search for a specific project based on his name
    function readOneName(){
  
        // query to read single record
        $query = "SELECT id, naam
                  FROM project 
                  WHERE naam = ?
                  LIMIT 0,1";
      
        // prepare query statement
        $stmt = $this->conn->prepare( $query );
      
        // bind id of project
        $stmt->bindParam(1, $this->naam);
      
        // execute query
        $stmt->execute();
      
        // get retrieved row
        $row = $stmt->fetch(PDO::FETCH_ASSOC);
      
        // set values to object properties
        $this->id = $row['id'];
        }

    // create project
    function create(){
  
    // query to insert record
    $query = "INSERT INTO project
                SET naam=:naam";
  
    // prepare query
    $stmt = $this->conn->prepare($query);
  
    // sanitize
    $this->naam=htmlspecialchars(strip_tags($this->naam));
  
    // bind values
    $stmt->bindParam(":naam", $this->naam);
  
    // execute query
    if($stmt->execute()){
        return true;
    }
  
    return false;
    }

}
?>