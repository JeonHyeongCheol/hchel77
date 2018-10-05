library(shiny)
#head(mtcars)

# Define UI for application that draws a histogram
ui <- fluidPage(
  # Application title
  titlePanel("표나 플롯 중에서 선택하기"),
  
  radioButtons("selected", label = "항목 선택",
               choices = list("Table", "Plot")),
  uiOutput("tbl2"),
  uiOutput("plot2")
  
)

# Define server logic required to draw a histogram
server <- function(input, output) {
  output$tbl <- renderTable({
    mtcars
  })
  
  output$tbl2 <- renderUI({ 
    if(input$selected == "Table") { # Radio 버튼 Table 클릭시 위에서 만든 tbl 테이블 함수를 가져와 뿌려줌.
      tableOutput("tbl")
    }
  })
  
  output$plt <- renderPlot({
    plot(mtcars$wt, mtcars$mpg)
  })
  
  output$plot2 <- renderUI({
    if(input$selected == "Plot") { # Radio 버튼 Plot 클릭시 위에서 만든 plt 그래프 함수를 가져와 뿌려줌.
      plotOutput("plt")
    }
  })
  
}

# Run the application 
shinyApp(ui = ui, server = server)

