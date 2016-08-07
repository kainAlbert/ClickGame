package Object;

import java.util.List;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;

public class Collision {

	// 更新
	public void update(){

		List<CharacterBase> baseList = Application.getObj().getCM().getBaseList();
		List<CharacterBase> pbList = Application.getObj().getCM().getPlayerBulletList();
		List<CharacterBase> ebList = Application.getObj().getCM().getEnemyBulletList();


		for( int i=0; i<baseList.size(); i++ ){

			if( baseList.get(i).getType() != Define.BASE_TYPE.BASE.ordinal() ) continue;

			// 各本拠地と弾の当たり判定
			if( baseList.get(i).getForce() == Define.TYPE_PLAYER ){

				collisionBulletBase( baseList.get(i), ebList );
			}
			if( baseList.get(i).getForce() == Define.TYPE_ENEMY ){

				collisionBulletBase( baseList.get(i), pbList );
			}
		}
	}

	// 弾と本拠地の当たり判定
	private void collisionBulletBase( CharacterBase base, List<CharacterBase> bList ){

		for( int i=0; i<bList.size(); i++ ){

			CharacterBase b = bList.get(i);

			if( !isCollisionSquare( base.getPos(), base.getSize(), b.getPos(), b.getSize() ) ) continue;

			base.collision();
			b.collision();
		}
	}

	// 弾とガードの当たり判定
	public void collisionBulletGuard( CharacterBase base, List<CharacterBase> bList ){

		if( base == null || bList == null ) return;

		for( int i=0; i<bList.size(); i++ ){

			CharacterBase b = bList.get(i);

			if( !isCollisionCircle( base.getPos(), Define.GUARD_DISTANCE, b.getPos(), b.getSize().x / 2 ) ) continue;

			b.collision();
		}
	}

	// 正方形同士の判定
	private boolean isCollisionSquare( GSvector2 pos1, GSvector2 scale1, GSvector2 pos2, GSvector2 scale2 ){

		boolean cx =
				( pos1.x <= pos2.x && pos1.x + scale1.x >= pos2.x ) ||
				( pos1.x <= pos2.x + scale2.x && pos1.x + scale1.x >= pos2.x + scale2.x );

		boolean cy =
				( pos1.y <= pos2.y && pos1.y + scale1.y >= pos2.y ) ||
				( pos1.y <= pos2.y + scale2.y && pos1.y + scale1.y >= pos2.y + scale2.y );

		boolean cxy1 =
				( pos1.x <= pos2.x && pos1.x + scale1.x >= pos2.x + scale2.x ) &&
				( pos2.y <= pos1.y && pos2.y + scale2.y >= pos1.y + scale1.y );

		boolean cxy2 =
				( pos2.x <= pos1.x && pos2.x + scale2.x >= pos1.x + scale1.x ) &&
				( pos1.y <= pos2.y && pos1.y + scale1.y >= pos2.y + scale2.y );

		return ( cx && cy ) || cxy1 || cxy2;
	}

	// 点と円の判定
	private boolean isCollisionPointCircle( GSvector2 pos1, GSvector2 pos2, double radius ){

		return Math.pow( pos1.x - pos2.x, 2 ) + Math.pow( pos1.y - pos2.y, 2 ) < Math.pow( radius, 2 );
	}

	// 正方形と円の判定
	private boolean isCollisionSquareCircle( GSvector2 pos1, GSvector2 scale1, GSvector2 pos2, double radius2 ){

		GSvector2 pos2_r = new GSvector2( pos2.x + radius2, pos2.y + radius2 );

		GSvector2[] pos = {
				new GSvector2( pos1.x, pos1.y ), new GSvector2( pos1.x + scale1.x, pos1.y ),
				new GSvector2( pos1.x, pos1.y + scale1.y ), new GSvector2( pos1.x + scale1.x, pos1.y + scale1.y )
		};

		for( int i=0; i<4; i++ ){

			if( isCollisionPointCircle( pos[i], pos2_r, radius2 ) ) return true;
		}

		if( pos1.x < pos2_r.x - radius2 &&
				pos1.x + scale1.x > pos2_r.x + radius2 &&
				pos1.y < pos2_r.y - radius2 &&
				pos1.y + scale1.y > pos2_r.y + radius2 ) return true;

		return false;
	}

	// 円と円の判定
	private boolean isCollisionCircle( GSvector2 pos1, double radius1, GSvector2 pos2, double radius2 ){

		return Math.pow( pos1.x - pos2.x, 2 ) + Math.pow( pos1.y - pos2.y, 2 ) < Math.pow( radius1 + radius2, 2 );
	}
}
