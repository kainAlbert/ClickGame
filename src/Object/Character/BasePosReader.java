package Object.Character;

import java.awt.Container;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import Application.Application;
import Application.Define;
import Application.GSvector2;

public class BasePosReader {

	// 読み込み
	public static void readBasePos( Application app, Container c ){

		BufferedReader br = null;
		String str = "";
		String[] item = null;
		int posY = 0;
		int number = 0;

		try{
			File file = new File( "txt/basePos.txt" );
			br = new BufferedReader(new FileReader(file));

		}catch( FileNotFoundException e ){
			e.printStackTrace();
		}

		// 1行目を無視するので2回読み込む
		try{
			str = br.readLine();
			str = br.readLine();
		}catch( Exception e ){}

		// 最後まで
		while( str != null ){

			try{

				item = str.split("\t");

				// 先頭の要素が-1なら終了
				if( item[0].equals("-1") ) break;

				// 1列目は無視するのでi=1から最後まで
				for( int i=1; i<item.length; i++ ){

					// -1なら終了
					if( item[i].equals( "-1" ) ) break;

					// 空白は無視
					if( item[i].equals("") ) continue;

					GSvector2 pos = new GSvector2( ( i - 1 ) * 20, posY );

					createBase( app, c, pos, number, Integer.parseInt(item[i]) );

					number++;
				}

				str = br.readLine();
				posY += 20;

			}catch( Exception e ){

				e.printStackTrace();
			}
		}

	}

	// ベース作成
	private static void createBase( Application app, Container c, GSvector2 pos, int number, int type ){

		CharacterBase b = null;

		if( type == Define.BASE_TYPE.BASE.ordinal() ){}

		if( type == Define.BASE_TYPE.ATTACK.ordinal() ){
			b = new AttackBase( app, c, pos, number, type );
		}

		if( type == Define.BASE_TYPE.ENERGY.ordinal() ){
			b = new EnergyBase( app, c, pos, number, type );
		}

		if( type == Define.BASE_TYPE.GUARD.ordinal() ){
			b = new GuardBase( app, c, pos, number, type );
		}

		Application.getObj().getCM().addBaseList( b );
	}

}
