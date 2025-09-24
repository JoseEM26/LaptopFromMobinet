using ExamenT2.Models;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Diagnostics;
using System.Linq;
using System.Web;

namespace ExamenT2.DAO.List
{
    public class ItemList
    {
        public List<item> SeleccionarTodo()
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;
            List<item> lista = new List<item>();
            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("USP_LISTAR_ITEM", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;


                reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    item Item = new item()
                    {
                        ItemID = Convert.ToInt32(reader[0]),
                        Descripcion = reader[1].ToString() 
                    };

                    lista.Add(Item);
                }




            }
            catch (Exception e)
            {
                Debug.WriteLine(e.Message);
            }
            finally
            {
                con.Close();
                reader.Close();
            }
            return lista;
        }

    }
}