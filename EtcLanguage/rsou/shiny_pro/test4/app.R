library(shiny)
library(ggplot2)

# Define UI for application that draws a histogram
ui <- fluidPage(
   
   # Application title
   titlePanel("Diamonds Data"),
   selectInput("plotType", "플롯타입", 
               c(Scatter = "scatter", Histogram = "hist")), # select 버튼만듬.
   conditionalPanel( # select 와 비슷하지만.. 뭐지?
     condition = "input.plotType == hist",
     selectInput(
       "breaks", "구간지정", c("Sturges", "Scott", "[Custom]"="custom")
     ),
     conditionalPanel( # conditionalpanel 안에 또 conditionalpanel을 줄 수 있음.
       condition = "input.breaks == custom",
       sliderInput("breakCount", "break Count", min = 1, max = 50, value = 10)
     )
   ),
   plotOutput("plot")
   
)

# Define server logic required to draw a histogram
server <- function(input, output) {
  brs <- reactive({ # 반응식 생성함수
    if(input$breaks == "custom") { # custom 값이 들어올 때
      input$breakCount
    } else {
      input$breaks
    }
  })
  
  p <- reactive({ # 반응이 들어오면 p에게 값을 줌. 위에도 동일
    if(input$plotType == "scatter") { # scatter 값이 들어올 때
      plot(diamonds$carat, diamonds$price, col="red") # 산포도
    } else {
      hist(diamonds$carat, breaks = brs()) # 히스토그램
    }
  })
  
  output$plot <- renderPlot({
    p()
  })
}

# Run the application 
shinyApp(ui = ui, server = server)