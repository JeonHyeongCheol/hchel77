'''
Created on 2018. 11. 14.

wxFormBuilder
'''
import wx
import wx.xrc

# -------------------------------- wxFormBuilder 프로그램 사용 -------------------------------- 

class ExamForm ( wx.Frame ):
    
    def __init__( self, parent ):
            wx.Frame.__init__ ( self, parent, id = wx.ID_ANY, title = u"연습용", pos = wx.DefaultPosition, size = wx.Size( 277,300 ), style = wx.DEFAULT_FRAME_STYLE|wx.TAB_TRAVERSAL )
            
            #self.SetSizeHintsSz( wx.DefaultSize, wx.DefaultSize )
            self.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_MENU ) )
            
            bSizer1 = wx.BoxSizer( wx.VERTICAL )
            
            self.m_panel1 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
            bSizer4 = wx.BoxSizer( wx.HORIZONTAL )
            
            self.m_staticText2 = wx.StaticText( self.m_panel1, wx.ID_ANY, u"이름 :", wx.Point( -1,-1 ), wx.DefaultSize, 0 )
            self.m_staticText2.Wrap( -1 )
            bSizer4.Add( self.m_staticText2, 0, wx.ALL, 5 )
            
            self.txtName = wx.TextCtrl( self.m_panel1, wx.ID_ANY, wx.EmptyString, wx.Point( -1,-1 ), wx.Size( 200,-1 ), 0 )
            bSizer4.Add( self.txtName, 0, wx.ALL, 5 )
            
            
            self.m_panel1.SetSizer( bSizer4 )
            self.m_panel1.Layout()
            bSizer4.Fit( self.m_panel1 )
            bSizer1.Add( self.m_panel1, 0, wx.EXPAND |wx.ALL, 5 )
            
            self.m_panel2 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
            bSizer7 = wx.BoxSizer( wx.HORIZONTAL )
            
            self.m_staticText3 = wx.StaticText( self.m_panel2, wx.ID_ANY, u"나이 :", wx.DefaultPosition, wx.DefaultSize, 0 )
            self.m_staticText3.Wrap( -1 )
            bSizer7.Add( self.m_staticText3, 0, wx.ALL, 5 )
            
            self.txtAge = wx.TextCtrl( self.m_panel2, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.Size( 100,-1 ), 0 )
            bSizer7.Add( self.txtAge, 0, wx.ALL, 5 )
            
            self.btnOk = wx.Button( self.m_panel2, wx.ID_ANY, u"확인", wx.DefaultPosition, wx.DefaultSize, 0 )
            bSizer7.Add( self.btnOk, 0, wx.ALL, 5 )
            
            
            self.m_panel2.SetSizer( bSizer7 )
            self.m_panel2.Layout()
            bSizer7.Fit( self.m_panel2 )
            bSizer1.Add( self.m_panel2, 0, wx.ALL|wx.EXPAND, 5 )
            
            self.m_panel3 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
            bSizer8 = wx.BoxSizer( wx.HORIZONTAL )
            
            self.m_staticText4 = wx.StaticText( self.m_panel3, wx.ID_ANY, u"결과보기 :", wx.DefaultPosition, wx.DefaultSize, 0 )
            self.m_staticText4.Wrap( -1 )
            bSizer8.Add( self.m_staticText4, 0, wx.ALL, 5 )
            
            self.staShow = wx.StaticText( self.m_panel3, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
            self.staShow.Wrap( -1 )
            bSizer8.Add( self.staShow, 0, wx.ALL, 5 )
            
            
            self.m_panel3.SetSizer( bSizer8 )
            self.m_panel3.Layout()
            bSizer8.Fit( self.m_panel3 )
            bSizer1.Add( self.m_panel3, 1, wx.EXPAND |wx.ALL, 5 )
            
            
            self.SetSizer( bSizer1 )
            self.Layout()
            
            self.Centre( wx.BOTH )
            
            # Connect Events
            self.btnOk.Bind( wx.EVT_BUTTON, self.OnMyClick )
    
    def __del__( self ):
        pass
    
    
    # Virtual event handlers, overide them in your derived class
    def OnMyClick( self, event ):
        #print("success")
        name = self.txtName.GetValue()
        age = self.txtAge.GetValue()
        if name == "": # name txt에 값이 빈값이면.
            wx.MessageBox("이름 입력", "알림", wx.OK) # 알림창
            return
        if age == "" : # age txt에 값이 빈값이면.
            wx.MessageBox("나이 입력", "알림", wx.OK)
            return

        self.staShow.SetLabel(name + '님의 나이는 ' + age)
# -------------------------------- wxFormBuilder 프로그램 사용 -------------------------------- 

if __name__ == '__main__':
    app = wx.App() 
    ExamForm(None).Show() # wxFormBuilder을 사용하면 Show가 없기 때문에 Show라고 써주면 켜짐.
    app.MainLoop()