<?php
class Gebouw{
  
    // database connection and table name
    private $conn;
    private $table_name = "gebouw";
  
    // object properties
    public $id;
    public $naam;
    public $hoogte;
    public $adres;
    public $postcode;
    public $gemeente;
    public $functie;
    public $projectId;
    
  
    // constructor with $db as database connection
    public function __construct($db){
        $this->conn = $db;
    }

    // // read gebouwen
    // function read(){

  
    // //select all query
    //    $query = 
    //    "SELECT id, naam, hoogte, adres, postcode, gemeente, functie, projectId
    //     FROM gebouw";

    // // prepare query statement
    // $stmt = $this->conn->prepare($query);
  
    // // execute query
    // $stmt->execute();
  
    // return $stmt;
    // }


    // read all products with specific ID
    function readByProjectId($projectId){
  
        // select all query
        $query = "SELECT id, naam, adres, postcode, gemeente, hoogte, functie, projectId
                    FROM gebouw
                    WHERE projectId = ?";
    
        // prepare query statement
        $stmt = $this->conn->prepare($query);
    
        // // sanitize
        // $projectId=htmlspecialchars(strip_tags($projectId));
        // $projectId = "%{$projectId}%";
    
        // bind
        $stmt->bindParam(1, $projectId);
    
        // execute query
        $stmt->execute();
  
    return $stmt;
}

    
    // used when you want a specific gebouw
    function readOne(){
  
    // query to read single record
    $query = "SELECT id, naam, hoogte, adres, postcode, gemeente, functie, projectId
              FROM gebouw 
              WHERE id = ?
              LIMIT 0,1";
  
    // prepare query statement
    $stmt = $this->conn->prepare( $query );
  
    // bind id of gebouw
    $stmt->bindParam(1, $this->id);
  
    // execute query
    $stmt->execute();
  
    // get retrieved row
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
  
    // set values to object properties
    $this->naam = $row['naam'];
    $this->hoogte = $row['hoogte'];
    $this->adres = $row['adres'];
    $this->postcode = $row['postcode'];
    $this->gemeente = $row['gemeente'];
    $this->functie = $row['functie'];
    $this->projectId = $row['projectId'];
    }

    // create gebouw
    function create(){
  
        // query to insert record
        $query = "INSERT INTO gebouw
                    SET naam=:naam, hoogte=:hoogte,adres=:adres,postcode=:postcode,gemeente=:gemeente,functie=:functie,projectId=:projectId";
      
        // prepare query
        $stmt = $this->conn->prepare($query);
      
        // sanitize
        $this->naam=htmlspecialchars(strip_tags($this->naam));
        $this->hoogte=htmlspecialchars(strip_tags($this->hoogte));
        $this->adres=htmlspecialchars(strip_tags($this->adres));
        $this->postcode=htmlspecialchars(strip_tags($this->postcode));
        $this->gemeente=htmlspecialchars(strip_tags($this->gemeente));
        $this->functie=htmlspecialchars(strip_tags($this->functie));
        $this->projectId=htmlspecialchars(strip_tags($this->projectId));
      
        // bind values
        $stmt->bindParam(":naam", $this->naam);
        $stmt->bindParam(":hoogte", $this->hoogte);
        $stmt->bindParam(":adres", $this->adres);
        $stmt->bindParam(":postcode", $this->postcode);
        $stmt->bindParam(":gemeente", $this->gemeente);
        $stmt->bindParam(":functie", $this->functie);
        $stmt->bindParam(":projectId", $this->projectId);
      
        // execute query
        if($stmt->execute()){
            return true;
        }
      
        return false;
        }
}
?>