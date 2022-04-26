'''
Created on 2018. 11. 15.

데이터 이동
'''
# -*- coding: utf-8 -*- 

###########################################################################
## Python code generated with wxFormBuilder (version Jun 17 2015)
## http://www.wxformbuilder.org/
##
## PLEASE DO "NOT" EDIT THIS FILE!
###########################################################################

import wx
import wx.xrc

###########################################################################
## Class MyFrame2
###########################################################################

import MySQLdb

# 데이터 이동용
datas = [] 
rec_r = 0

config = { # dic 타입!
    'host':'127.0.0.1',
    'user':'root',
    'password':'123',
    'database':'test',
    'port':3306,
    'charset':'utf8',
    'use_unicode':True
}

class MyForm ( wx.Frame ):
    
    def __init__( self, parent ):
        wx.Frame.__init__ ( self, parent, id = wx.ID_ANY, title = wx.EmptyString, pos = wx.DefaultPosition, size = wx.Size( 412,117 ), style = wx.DEFAULT_FRAME_STYLE|wx.TAB_TRAVERSAL )
        
        #self.SetSizeHintsSz( wx.DefaultSize, wx.DefaultSize )
        self.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_INFOBK ) )
        
        bSizer9 = wx.BoxSizer( wx.VERTICAL )
        
        self.m_panel5 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer10 = wx.BoxSizer( wx.VERTICAL )
        
        self.staShow = wx.StaticText( self.m_panel5, wx.ID_ANY, u"자료보기", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.staShow.Wrap( -1 )
        bSizer10.Add( self.staShow, 0, wx.ALL, 5 )
        
        
        self.m_panel5.SetSizer( bSizer10 )
        self.m_panel5.Layout()
        bSizer10.Fit( self.m_panel5 )
        bSizer9.Add( self.m_panel5, 0, wx.ALL|wx.EXPAND, 5 )
        
        self.m_panel6 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer11 = wx.BoxSizer( wx.HORIZONTAL )
        
        self.btn1 = wx.Button( self.m_panel6, wx.ID_ANY, u"처음", wx.DefaultPosition, wx.Size( -1,50 ), 0 )
        bSizer11.Add( self.btn1, 0, wx.ALL, 5 )
        
        self.btn2 = wx.Button( self.m_panel6, wx.ID_ANY, u"이전", wx.DefaultPosition, wx.Size( -1,50 ), 0 )
        bSizer11.Add( self.btn2, 0, wx.ALL, 5 )
        
        self.btn3 = wx.Button( self.m_panel6, wx.ID_ANY, u"다음", wx.DefaultPosition, wx.Size( -1,50 ), 0 )
        bSizer11.Add( self.btn3, 0, wx.ALL, 5 )
        
        self.btn4 = wx.Button( self.m_panel6, wx.ID_ANY, u"마지막", wx.DefaultPosition, wx.Size( -1,50 ), 0 )
        bSizer11.Add( self.btn4, 0, wx.ALL, 5 )
        
        
        self.m_panel6.SetSizer( bSizer11 )
        self.m_panel6.Layout()
        bSizer11.Fit( self.m_panel6 )
        bSizer9.Add( self.m_panel6, 0, wx.ALL|wx.EXPAND, 5 )
        
        
        self.SetSizer( bSizer9 )
        self.Layout()
        
        self.Centre( wx.BOTH )
        
        # Connect Events
        self.btn1.id = 1
        self.btn2.id = 2
        self.btn3.id = 3
        self.btn4.id = 4
        
        self.btn1.Bind( wx.EVT_BUTTON, self.OnProcess )
        self.btn2.Bind( wx.EVT_BUTTON, self.OnProcess )
        self.btn3.Bind( wx.EVT_BUTTON, self.OnProcess )
        self.btn4.Bind( wx.EVT_BUTTON, self.OnProcess )
    
    def __del__( self ):
        pass
    
    
    # Virtual event handlers, overide them in your derived class
    def OnProcess( self, event ):
        #print(event.GetEventObject().id)
        id = event.GetEventObject().id
        global rec_r
        
        if id == 1: # 처음
            rec_r = 0
        elif id == 2: # 이전
            rec_r = rec_r -1
            if rec_r < 0:
                rec_r = 0 # 맨 처음 레코드일 때 0으로 설정.
                return
        elif id == 3: # 다음
            rec_r = rec_r + 1
            if rec_r > (len(datas) - 1): # 맨 마지막일 때 값 그대로 가지고 있음.
                rec_r = len(datas) - 1
                return
        elif id == 4: # 마지막
            rec_r = len(datas) -1
        
        #print(rec_r)
        
        self.ShowData(rec_r)
        
    def ShowData(self, rec_r):
        try:
            conn = MySQLdb.connect(**config)
            cursor = conn.cursor()
            sql = "select * from sangdata"
            cursor.execute(sql)
            
            global datas
            datas = cursor.fetchall()
            #print(datas)
            #print(datas[0])
            #print(datas[0][0])
            self.ShowDatas(rec_r)
            
        except Exception as e:
            print("DBLoding Err : ", e)
        finally:
            cursor.close()
            conn.close()
            
    def ShowDatas(self, r):
        text = str(datas[r][0]) + " " + datas[r][1] + " " + str(datas[r][2]) + " " + str(datas[r][3])
        # text = 코드 + 
        self.staShow.SetLabelText(text)
    
if __name__ == '__main__':
    app = wx.App() 
    MyForm(None).Show()
    app.MainLoop()

