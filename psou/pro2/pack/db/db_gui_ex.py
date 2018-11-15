'''
Created on 2018. 11. 15.

@author: kitcoop
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
## Class MyFrame3
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
        wx.Frame.__init__ ( self, parent, id = wx.ID_ANY, title = wx.EmptyString, pos = wx.DefaultPosition, size = wx.Size( 414,384 ), style = wx.DEFAULT_FRAME_STYLE|wx.TAB_TRAVERSAL )
        
        #self.SetSizeHintsSz( wx.DefaultSize, wx.DefaultSize )
        self.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_INFOBK ) )
        
        bSizer12 = wx.BoxSizer( wx.VERTICAL )
        
        self.m_panel7 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer13 = wx.BoxSizer( wx.HORIZONTAL )
        
        self.m_staticText8 = wx.StaticText( self.m_panel7, wx.ID_ANY, u"사번 : ", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText8.Wrap( -1 )
        bSizer13.Add( self.m_staticText8, 0, wx.ALL, 5 )
        
        self.txtBun = wx.TextCtrl( self.m_panel7, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.Size( 50,-1 ), 0 )
        bSizer13.Add( self.txtBun, 0, wx.ALL, 5 )
        
        self.m_staticText9 = wx.StaticText( self.m_panel7, wx.ID_ANY, u"이름 : ", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText9.Wrap( -1 )
        bSizer13.Add( self.m_staticText9, 0, wx.ALL, 5 )
        
        self.txtName = wx.TextCtrl( self.m_panel7, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.Size( 100,-1 ), 0 )
        bSizer13.Add( self.txtName, 0, wx.ALL, 5 )
        
        self.m_staticText10 = wx.StaticText( self.m_panel7, wx.ID_ANY, u"직급 :", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText10.Wrap( -1 )
        bSizer13.Add( self.m_staticText10, 0, wx.ALL, 5 )
        
        self.txtJik = wx.TextCtrl( self.m_panel7, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.Size( 75,-1 ), 0 )
        bSizer13.Add( self.txtJik, 0, wx.ALL, 5 )
        
        
        self.m_panel7.SetSizer( bSizer13 )
        self.m_panel7.Layout()
        bSizer13.Fit( self.m_panel7 )
        bSizer12.Add( self.m_panel7, 0, wx.EXPAND |wx.ALL, 5 )
        
        self.m_panel8 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer14 = wx.BoxSizer( wx.HORIZONTAL )
        
        self.m_staticText11 = wx.StaticText( self.m_panel8, wx.ID_ANY, u"부서명 : ", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText11.Wrap( -1 )
        bSizer14.Add( self.m_staticText11, 0, wx.ALL, 5 )
        
        self.txtBuser = wx.TextCtrl( self.m_panel8, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.Size( 100,-1 ), 0 )
        bSizer14.Add( self.txtBuser, 0, wx.ALL, 5 )
        
        
        self.m_panel8.SetSizer( bSizer14 )
        self.m_panel8.Layout()
        bSizer14.Fit( self.m_panel8 )
        bSizer12.Add( self.m_panel8, 0, wx.EXPAND |wx.ALL, 5 )
        
        self.m_panel9 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer15 = wx.BoxSizer( wx.HORIZONTAL )
        
        self.btn1 = wx.Button( self.m_panel9, wx.ID_ANY, u"처음", wx.DefaultPosition, wx.DefaultSize, 0 )
        bSizer15.Add( self.btn1, 0, wx.ALL, 5 )
        
        self.btn2 = wx.Button( self.m_panel9, wx.ID_ANY, u"이전", wx.DefaultPosition, wx.DefaultSize, 0 )
        bSizer15.Add( self.btn2, 0, wx.ALL, 5 )
        
        self.btn3 = wx.Button( self.m_panel9, wx.ID_ANY, u"다음", wx.DefaultPosition, wx.DefaultSize, 0 )
        bSizer15.Add( self.btn3, 0, wx.ALL, 5 )
        
        self.btn4 = wx.Button( self.m_panel9, wx.ID_ANY, u"마지막", wx.DefaultPosition, wx.DefaultSize, 0 )
        bSizer15.Add( self.btn4, 0, wx.ALL, 5 )
        
        
        self.m_panel9.SetSizer( bSizer15 )
        self.m_panel9.Layout()
        bSizer15.Fit( self.m_panel9 )
        bSizer12.Add( self.m_panel9, 0, wx.EXPAND |wx.ALL, 5 )
        
        self.m_panel10 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer16 = wx.BoxSizer( wx.VERTICAL )
        
        self.lstView = wx.ListCtrl( self.m_panel10, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.LC_REPORT )
        bSizer16.Add( self.lstView, 0, wx.ALL|wx.EXPAND, 5 )
        
        
        self.m_panel10.SetSizer( bSizer16 )
        self.m_panel10.Layout()
        bSizer16.Fit( self.m_panel10 )
        bSizer12.Add( self.m_panel10, 0, wx.EXPAND |wx.ALL, 5 )
        
        self.m_panel11 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer17 = wx.BoxSizer( wx.VERTICAL )
        
        self.staCount = wx.StaticText( self.m_panel11, wx.ID_ANY, u"고객 수 : 0명", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.staCount.Wrap( -1 )
        bSizer17.Add( self.staCount, 0, wx.ALL, 5 )
        
        
        self.m_panel11.SetSizer( bSizer17 )
        self.m_panel11.Layout()
        bSizer17.Fit( self.m_panel11 )
        bSizer12.Add( self.m_panel11, 0, wx.EXPAND |wx.ALL, 5 )
        
        
        self.SetSizer( bSizer12 )
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
        
        self.lstView.InsertColumn(0, '고객번호', width=100)
        self.lstView.InsertColumn(1, '고객명')
        self.lstView.InsertColumn(2, '고객전화')
    
    def __del__( self ):
        pass
    
    
    # Virtual event handlers, overide them in your derived class
    def OnProcess( self, event ):
        id = event.GetEventObject().id
        global rec
        
        if id == 1:
            rec = 0
        elif id == 2:
            rec = rec - 1
            if rec < 0:
                rec = 0
                return
        elif id == 3:
            rec = rec + 1
            if rec > (len(datas) - 1):
                rec = len(datas) - 1
                return
        elif id == 4:
            rec = len(datas) - 1
            
        self.ShowData(rec)
        
    def ShowData(self, rec):
        try:
            conn = MySQLdb.connect(**config)
            cursor = conn.cursor()
            sql = """
            select jikwon_no, jikwon_name, jikwon_jik, buser_name 
            from jikwon inner join buser on buser_num = buser_no
            order by jikwon_no asc        
            """
            cursor.execute(sql)
        
            global datas
            datas = cursor.fetchall()
            self.ShowDatas(rec)
    
        except Exception as e:
            print("DB Loding Err : ", e)
            
        finally: 
            cursor.close()
            conn.close()
            
    def ShowDatas(self, rec):
        bun = str(datas[rec][0])
        name = datas[rec][1]
        jik = datas[rec][2]
        buser = datas[rec][3]
        
        self.txtBun.SetLabelText(bun)
        self.txtName.SetLabelText(name)
        self.txtJik.SetLabelText(jik)
        self.txtBuser.SetLabelText(buser)
        
        self.ShowGogeks(bun)
        
    def ShowGogeks(self, damsano):
        try:
            conn = MySQLdb.connect(**config)
            cursor = conn.cursor()
            
            sql = """
            select gogek_no, gogek_name, gogek_tel
            from gogek
            where gogek_damsano={}
            """.format(damsano)
            
            cursor.execute(sql)
            gdatas = cursor.fetchall()
            
            self.lstView.DeleteAllItems()
            
            for g in gdatas:
                i = self.lstView.InsertItem(65000, 0)
                self.lstView.SetItem(i, 0, str(g[0]))
                self.lstView.SetItem(i, 1, g[1])
                self.lstView.SetItem(i, 2, g[2])  
                
            self.staCount.SetLabelText("고객 수 : " + str(len(gdatas)) + "명")
            
        except Exception as e :
            print("ShowGogeks Err : ", e)
        
        finally:
            cursor.close()
            conn.close()
    
if __name__ == '__main__':
    app = wx.App() 
    MyForm(None).Show()
    app.MainLoop()