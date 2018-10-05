#install.packages("shiny")
#install.packages("DBI")
#install.packages("dplyr")
#install.packages("dbplyr")
#install.packages("RMySQL")
library(shiny)
library(DBI)
library(dplyr)
library(dbplyr)
library(RMySQL)

ui <- fluidPage(
  numericInput("nrows", "Enter the number of rows to display:", 5),
  tableOutput("tbl")
)

server <- function(input, output, session) {
  output$tbl <- renderTable({
    conn <- dbConnect(
      drv = RMySQL::MySQL(),
      dbname = "shinydemo",
      host = "shiny-demo.csa7qlmguqrf.us-east-1.rds.amazonaws.com",
      username = "guest",
      password = "guest")
    on.exit(dbDisconnect(conn), add = TRUE)
    dbGetQuery(conn, paste0(
      "SELECT * FROM City LIMIT ", input$nrows, ";"))
  })
}

shinyApp(ui, server)