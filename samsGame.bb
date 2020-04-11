; Maxwell's Demon
; -------------------

width=800
height=600
depth=16
mode=2

Graphics3D width,height,depth,mode
SetBuffer BackBuffer()

Type Player

	Field px#, py#, pz#

End Type

p.Player = New Player
p\px# = 0
p\py# = 10
p\pz# = -50


cam=CreateCamera()
PositionEntity cam,0,10,-50


light=CreateLight()
RotateEntity light,90,0,0





;create suspects

;left suspect
sus1=LoadMD2("media/gargoyle.md2")
ScaleEntity sus1,.2,.2,.2
PositionEntity sus1,-40,0,50
RotateEntity sus1, 0,180,0
EntityColor sus1, 153,153,153
;middle suspect
sus2=LoadMD2("media/gargoyle.md2")
ScaleEntity sus2,.2,.2,.2
PositionEntity sus2,0,0,50
RotateEntity sus2, 0,180,0
EntityColor sus2, 0,53,0
;right suspect
sus3=LoadMD2("media/gargoyle.md2")
ScaleEntity sus3,.2,.2,.2
PositionEntity sus3, 40,0,50
RotateEntity sus3, 0,180,0
EntityColor sus3, 105, 51, 51


;create rooms

;left room
room1=CreateCube()
EntityColor room1,0,102,102
FitMesh room1,-60,-10,30,40,40,40
FlipMesh room1
;middle room
room2=CreateCube()
EntityColor room2,0,102,102
FitMesh room2,-20,-10,30,40,40,40
FlipMesh room2
;right room
room3=CreateCube()
EntityColor room3,0,102,102
FitMesh room3,20,-10,30,40,40,40
FlipMesh room3

;create desks


;left desk
desk1=LoadMesh("media/StylishDesk.3ds")
FitMesh desk1, -140, 0, 20, 200, 8, 50
FlipMesh desk1

;middle desk
desk2=LoadMesh("media/StylishDesk.3ds")
FitMesh desk2, -100, 0, 20, 200, 8, 50
FlipMesh desk2

;right desk
desk3=LoadMesh("media/StylishDesk.3ds")
FitMesh desk3, -60, 0, 20, 200, 8, 50
FlipMesh desk3


; Create some scenery

; ground
ground=CreatePlane()
;EntityColor ground,168,133,55
ground_tex=LoadTexture("media/blacklodge.jpg")
ScaleTexture ground_tex,10,10
EntityTexture ground,ground_tex

; sky
sky=CreateSphere(24)
ScaleEntity sky,500,500,500
EntityColor sky, 155,0,0
FlipMesh sky
EntityFX sky,1
sky_tex=LoadTexture("media/sky.bmp")
EntityTexture sky,sky_tex




While Not KeyDown(1)

	; Control camera
	
	; mouse look
	
	mxs#=(mxs#+(MouseXSpeed()/5.0)) Mod 360
	mys#=mys#+(MouseYSpeed()/5.0)

	RotateEntity cam,mys#,-mxs#,0

	MoveMouse width/2,height/2

	

	 ;move camera forwards/backwards/Left/Right with cursor keys
	
	If KeyDown(200)=True Then p\pz# = p\pz# + .3 ;MoveEntity cam,0,0,.3 ; move camera forward
	If KeyDown(208)=True Then p\pz# = p\pz# - .3;MoveEntity cam,0,0,-.3 ; move camera back

	If KeyDown(205)=True Then p\px# = p\px# + .3 ;MoveEntity cam,.3,0,0 ; move camera left
	If KeyDown(203)=True Then p\px# = p\px# - .3 ;MoveEntity cam,-.3,0,0 ; move camera right
	
	PositionEntity cam,p\px#,p\py#,p\pz#
	
	UpdateWorld
	RenderWorld
	
	Text 0,0,"Maxwell's Demon"+ mxs#
	Text 0,20,"Look with mouse, Move with arrow keys."+mys#

	Flip

Wend

End
;~IDEal Editor Parameters:
;~C#Blitz3D