package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.PhotoSpecial;
import idao.IPhotoSpecialDao;
import util.DBUtil;

public class PhotoSpecialDao implements IPhotoSpecialDao{
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	
	public Boolean insertPhotoSpecial(PhotoSpecial photoSpecial){
		conn=DBUtil.getConnect();
		boolean saveflag=true;
		try {
			pstm=conn.prepareStatement("INSERT INTO photospecial (speTitle,speContent,speDate) VALUES (?,?,?)");
			pstm.setString(1, photoSpecial.getSpeTitle());
			pstm.setString(2, photoSpecial.getSpeContent());
			pstm.setString(3, photoSpecial.getSpeDate());
			
			pstm.executeUpdate();
			
			DBUtil.close(conn, pstm, null);
			return saveflag;
		} catch (SQLException e) {
			// TODO: handle exception
			saveflag=false;
			return saveflag;
		}
		
	}
	public List<PhotoSpecial> findAll() throws SQLException{
		List<PhotoSpecial> list = new ArrayList<PhotoSpecial>();
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT * FROM photospecial ORDER BY speId DESC");
		rs=pstm.executeQuery();
		while(rs.next()){
			PhotoSpecial special = new PhotoSpecial(rs.getString(2),rs.getString(3),rs.getString(4));
			special.setSpeId(rs.getLong(1));
			list.add(special);
			
		}
		return list;
	}
	
	public PhotoSpecial findOne(Long speId){
		List<PhotoSpecial> list = new ArrayList<PhotoSpecial>();
		conn=DBUtil.getConnect();
		try {
			pstm=conn.prepareStatement("SELECT * FROM photospecial WHERE speId=?");
			pstm.setLong(1, speId);
			rs=pstm.executeQuery();
			while(rs.next()){
				PhotoSpecial photoSpecial = new PhotoSpecial(rs.getString(2),rs.getString(3),rs.getString(4));
				photoSpecial.setSpeId(rs.getLong(1));
				list.add(photoSpecial);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.size()==1?list.get(0):null;
	}

}
