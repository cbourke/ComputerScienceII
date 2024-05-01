

def file_contents_as_html(file_name):
    """
    """

  public static function fileGetContents($fileName) {
    $result = "<p>File: " . basename($fileName) . "</p>";
    if(file_exists($fileName)) {
      $result .= "<pre>" . htmlentities(file_get_contents($fileName)) . "</pre>";
    } else {
      $result .= "<p><span style='color: red; font-weight: bold;'>ERROR: </span>File, $fileName does not exist.  Cannot display contents.</p>";
    }
    return $result;
  }

class Tester:

    version = "0.0.1"
    collapse_div_id_counter = 100
