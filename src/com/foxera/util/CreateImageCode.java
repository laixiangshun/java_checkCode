package com.foxera.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class CreateImageCode {
	private static Random random=new Random();
	
	//生成图片
	public static void CreateImg(OutputStream out,String code){
		int width=160;
		int height=40;
		int codeCount=4;
		int lineCount=20;
		BufferedImage bufferImg=null;
		/*String code=null;*/
		int fontWidth=width/codeCount;//字体宽度
		int fontHeight=height-5;//字体高度
		int codeY=height-8;
		
		bufferImg=new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g=bufferImg.getGraphics();
		g.setColor(getRandColor(200,250));//设置背景色
		g.fillRect(0, 0, width, height);
		
		//设置字体
		Font font=new Font("Fixedsys",Font.BOLD,fontHeight);
		g.setFont(font);
		
		Color c=getRandColor(200, 250);
		shear(g, width, height, c);
		//画干扰线
		for(int i=0;i<lineCount;i++){
			int xs=random.nextInt(width);
			int ys=random.nextInt(height);
			int xe=xs+random.nextInt(width);
			int ye=ys+random.nextInt(height);
			g.setColor(getRandColor(1, 255));
			g.drawLine(xs, ys, xe, ye);
		}
		//添加噪点
		float yawpRate=0.05f;//噪声率
		int area=(int)yawpRate * width * height;
		for(int i=0;i<area;i++){
			int x=random.nextInt(width);
			int y=random.nextInt(height);
			bufferImg.setRGB(x, y, random.nextInt(255));
		}
		/*code=randomStr(codeCount);//获取随机字符*/	
		/*code=code;*/
		for(int i=0;i<codeCount;i++){
			String strrandom=code.substring(i, i+1);
			g.setColor(getRandColor(1, 255));
			//将文字旋转指定角度
			Graphics2D g2d=(Graphics2D)g;
			AffineTransform trans=new AffineTransform();
			//trans.rotate((45)*3.14/250,15*i+8,7);
			//缩放文字
			//float scaleSize=random.nextInt()+0.8f;
			//if(scaleSize>1f) scaleSize=1f;
			//trans.scale(scaleSize, scaleSize);
			trans.setToRotation(Math.PI /4*random.nextDouble()*(random.nextBoolean()?1:-1),(width/codeCount)*i+(height-4)/2,height/2);
			g2d.setTransform(trans);
			g.drawString(strrandom, i*fontWidth+3, codeY);
		}
		
		g.dispose();
		try {
			ImageIO.write(bufferImg, "jpg", out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取随机颜色
	private static Color getRandColor(int fc,int bc){
		if(fc>255){
			fc=255;
		}
		if(bc>255){
			bc=255;
		}
		int r=fc+random.nextInt(bc-fc);
		int g=fc+random.nextInt(bc-fc);
		int b=fc+random.nextInt(bc-fc);
		return new Color(r,g,b);
	}
	//获取随机验证码
	public static String randomStr(int codeCount){
		 String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		 String str2="";
		 int len=str1.length()-1;
		 double r;
		 for(int i=0;i<len;i++){
			 r=(Math.random())*len;
			 str2+=str1.charAt((int)r);
		 }
		 return str2;
	}
	//扭曲方法
	private static void shear(Graphics g,int w,int h,Color color){
		shearX(g,w,h,color);
		shearY(g,w,h,color);
	}
	private static void shearX(Graphics g,int w,int h,Color color){
		int period=random.nextInt(2);
		boolean borderGrp=true;
		int prames=1;
		int phase=random.nextInt(2);
		for(int i=0;i<h;i++){
			double d=(double) (period>>1)*Math.sin((double) i/(double)period+(6.2831853071795862D * (double)phase)/(double)prames);
			g.copyArea(0, i, w, 1, (int)d, 0);
			if(borderGrp){
				g.setColor(color);
				g.drawLine((int)d, i, 0, i);
				g.drawLine((int)d+w, i, w, i);
			}
		}
	}
	private static void shearY(Graphics g,int w,int h,Color color){
			int period=random.nextInt(40)+10;
			boolean borderGrp=true;
			int frames=20;
			int phase=7;
			for(int i=0;i<w;i++){
				double d=(double)(period>>1)*Math.sin((double)i/(double)period+(6.2831853071795862D * (double) phase)/(double)frames);
				g.copyArea(i, 0, 1, h, 0, (int)d);
				if(borderGrp){
					g.setColor(color);
					g.drawLine(i, (int)d, i, 0);
					g.drawLine(i, (int)d+h, i, h);
				}
			}
	}
}
