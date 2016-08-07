package Object.Character;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Application.Panel;

public class BasePosReader {

	// 読み込み
	public static void readBasePos( Application app, Panel p ){

		BufferedReader br = null;
		String str = "";
		String[] item = null;
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

				createBase(
						app, p,
						new GSvector2( Integer.parseInt(item[0]), Integer.parseInt(item[1]) ),
						number,
						Integer.parseInt(item[2])
					);

				str = br.readLine();
				number++;

			}catch( Exception e ){

				e.printStackTrace();
			}
		}

	}

	// ベース作成
	private static void createBase( Application app, Panel p, GSvector2 pos, int number, int type ){

		CharacterBase b = null;

		if( type == Define.BASE_TYPE.BASE.ordinal() ){
			b = new BaseBase( app, p, pos, number, type );
		}

		if( type == Define.BASE_TYPE.ENERGY.ordinal() ){
			b = new EnergyBase( app, p, pos, number, type );
		}

		if( type == Define.BASE_TYPE.ATTACK.ordinal() ){
			b = new AttackBase( app, p, pos, number, type );
		}

		if( type == Define.BASE_TYPE.GUARD.ordinal() ){
			b = new GuardBase( app, p, pos, number, type );
		}

		Application.getObj().getCM().addBaseList( b );
	}

}
