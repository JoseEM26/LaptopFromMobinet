using ExamenT2.Models;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Diagnostics;
using System.Linq;
using System.Web;

namespace ExamenT2.DAO.Busqueda
{
    public class AgenciaBusqueda
    {
        
        public List<AgenciaCompleta> Busqueda(int id,string nombre,string distrito)
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;
            List<AgenciaCompleta> lista = new List<AgenciaCompleta>();

            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_Buscar_Agencia", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@agenciaID", id);
                cmd.Parameters.AddWithValue("@nombre", nombre);
                cmd.Parameters.AddWithValue("@Distrito", distrito);

                reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    var obj = new AgenciaCompleta()
                    {
                        AgenciaID = reader.GetInt32(0),
                        Nombre=reader.GetString(1),
                        Direccion=reader.GetString(2),
                        Area = reader.GetString(3),
                        Distrito= reader.GetString(4),
                        Provincia= reader.GetString(5),
                        Departamento= reader.GetString(6),
                        
                        
                    };

                    lista.Add(obj);
                }


            }
            catch(Exception e)
            {
                Debug.WriteLine(e.Message);
            }
            finally
            {
                reader.Close();
                con.Close();
            }
            return lista;
        }
    }
}