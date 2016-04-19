<?php

class MyPDO {

    protected static $_instance;
    protected $_pdo;

    private function __construct() {
        try {
            $this->_pdo = new PDO(Config::$config["CONNECTION_STRING"], 
                                    Config::$config["USER"], 
                                    Config::$config["PASSWORD"], 
                                    array(PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8'));
            $this->_pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (Exception $e) {
            echo 'Erreur : ' . $e->getMessage() . '<br />';
            echo 'N° : ' . $e->getCode();
            die();
        }
    }

    /**
     * Empêche la copie externe de l'instance.
     */
    private function __clone() {
        throw new Exception("Clonage de " . __CLASS__ . " interdit !");
    }

    public function __get($name) {
        return $this->_pdo->$name;
    }

    public function __set($name, $val) {
        return $this->_pdo->$name = $val;
    }

    /**
     * Renvoi de l'instance et initialisation si nécessaire.
     */
    public static function getInstance() {
        if (!isset(self::$_instance)) {
            self::$_instance = new self;
        }
        return self::$_instance;
    }

    public function __call($method, $args) {
        return call_user_func_array(array($this->_pdo, $method), $args);
    }

    public static function executeQuery($sql, $params = null) {
        $mypdo = self::getInstance();
        if ($params != null) {
            $params2 = array();
            foreach ($params as $k => $param) {
                $params2[":" . $k] = $param;
            }
            try {
                $res = $mypdo->prepare($sql, array(PDO::ATTR_CURSOR => PDO::CURSOR_FWDONLY));
                $res->execute($params2);
            } catch (PDOException $e) {
                echo 'Erreur : ' . $e->getMessage() . '<br />';
                echo 'N° : ' . $e->getCode();
                die();
            }
        } else {
            try {
                $res = $mypdo->prepare($sql);
                $res->execute();
            } catch (PDOException $e) {
                echo 'Erreur : ' . $e->getMessage() . '<br />';
                echo 'N° : ' . $e->getCode();
                die();
            }
        }

        if (preg_match("#^SELECT#i", $sql)) {
            return $res->fetchAll();
        } else {
            return true;
        }
    }

}

?>