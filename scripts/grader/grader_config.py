"""
Configuration file for grader scripts.

"""
class Config:

    term = "Spring 2024"
    project_name = "YRLess Sales Management System"
    project_jar = "Project.jar"
    handin_directory = "/home/c-cbourke3/handin/CSCE156_1241/"
    test_case_dir = f"{handin_directory}/common/testCases/"

    # Phase 1
    data_converter_file_path = "./com/yrl/DataConverter.java"
    data_converter_class = "com.yrl.DataConverter"
    csv_files  = ["Persons.csv", "Stores.csv", "Items.csv"]
    xml_files  = ["Persons.xml", "Stores.xml", "Items.xml"]
    json_files = ["Persons.json", "Stores.json", "Items.json"]

    # Phase 2
    test_case_files = ["Persons.csv", "Stores.csv", "Items.csv", "Sales.csv", "SaleItems.csv", "output.txt"]
    report_file_path = "./com/yrl/SalesReport.java"
    report_class = "com.yrl.SalesReport"

    # Phase 3
    sql_schema_file     = "database.sql"
    sql_er_diagram_file = "database.png"
    sql_query_file      = "queries.sql"
    sql_account         = "c-cbourke3"
    sql_password        = "EeDie2Chuogh"

    # Phase 5+
    database_staging_file  = "SalesDataStaging.java"
    database_staging_class = "com.yrl.staging.SalesDataStaging"

    staging_dir = "staging"

    junit_jar = "junit-platform-console-standalone-1.9.2.jar"
    mysql_jar = "mysql-connector-j-8.2.0.jar"
    common_jars = "./lib/log4j-api-2.21.1.jar:./lib/log4j-core-2.21.1.jar:./lib/mysql-connector-j-8.2.0.jar"

config = Config()
