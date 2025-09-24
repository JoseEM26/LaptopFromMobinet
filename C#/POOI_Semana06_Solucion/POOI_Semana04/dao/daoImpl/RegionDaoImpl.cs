using POOI_Semana04.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;
using System.Configuration;
using System.Diagnostics;

namespace POOI_Semana04.dao.daoImpl
{
    public class RegionDaoImpl : IRegionDao
    {
        public List<Region> listado()
        {
            List<Region> lista = new List<Region>();
            SqlConnection con = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;
            string cadena = ConfigurationManager.ConnectionStrings["cnx_bd_hr"].ConnectionString;
            try
            {
                con = new SqlConnection(cadena);
                con.Open();
                cmd = new SqlCommand("usp_region_consultar", con );
                cmd.CommandType = System.Data.CommandType.StoredProcedure;

                reader = cmd.ExecuteReader();
                Region objReg;
                while(reader.Read())
                {
                    objReg = new Region();
                    objReg.region_id = reader.GetInt32(0);
                    objReg.region_name= reader.GetString(1);
                    lista.Add(objReg);
                }
            }catch (Exception ex)
            {
                Debug.WriteLine("Error : "  +ex.Message);
            }finally {
                reader.Close();
                con.Close();
            
            }
            return lista;
        }
    }
}