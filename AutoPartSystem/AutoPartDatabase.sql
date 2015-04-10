USE [PartsSystem]
GO
/****** Object:  Table [dbo].[Automovil]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Automovil](
	[Modelo] [varchar](50) NOT NULL,
	[Detalle] [varchar](50) NOT NULL,
	[AnioDeFabricacion] [date] NOT NULL,
 CONSTRAINT [PK_Automovil] PRIMARY KEY CLUSTERED 
(
	[Modelo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Cliente]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Cliente](
	[Nombre] [varchar](50) NOT NULL,
	[Estado] [varchar](50) NOT NULL,
	[EsPersona] [bit] NOT NULL,
 CONSTRAINT [PK_Cliente] PRIMARY KEY CLUSTERED 
(
	[Nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Engargado]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Engargado](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[Telefono] [int] NOT NULL,
	[Cargo] [varchar](50) NOT NULL,
	[Organizacion] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Engargado] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Fabricante]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Fabricante](
	[Nombre] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Fabricante] PRIMARY KEY CLUSTERED 
(
	[Nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Marca]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Marca](
	[Nombre] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Marca] PRIMARY KEY CLUSTERED 
(
	[Nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MarcasDePartes]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MarcasDePartes](
	[NombreParte] [varchar](50) NOT NULL,
	[NombreMarca] [varchar](50) NOT NULL,
 CONSTRAINT [PK_MarcasDePartes] PRIMARY KEY CLUSTERED 
(
	[NombreParte] ASC,
	[NombreMarca] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Orden]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Orden](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Fecha] [date] NOT NULL,
	[Impuesto] [int] NOT NULL,
	[MontoDeVenta] [int] NOT NULL,
	[TotalCobrado] [int] NOT NULL,
	[Cliente] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Orden] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Organizaciones]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Organizaciones](
	[CedulaJuridica] [int] NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[Direccion] [varchar](50) NULL,
	[Ciudad] [varchar](50) NULL,
	[IDEncargado] [int] NOT NULL,
 CONSTRAINT [PK_Organizaciones] PRIMARY KEY CLUSTERED 
(
	[Nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Parte]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Parte](
	[Nombre] [varchar](50) NOT NULL,
	[Precio] [int] NOT NULL,
	[Fabricante] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Parte] PRIMARY KEY CLUSTERED 
(
	[Nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PartesDeAutomovil]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PartesDeAutomovil](
	[NombreParte] [varchar](50) NOT NULL,
	[ModeloAutomovil] [varchar](50) NOT NULL,
 CONSTRAINT [PK_PartesDeAutomovil] PRIMARY KEY CLUSTERED 
(
	[NombreParte] ASC,
	[ModeloAutomovil] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PartesPorOrden]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PartesPorOrden](
	[IdOrden] [int] NOT NULL,
	[IdProveedor] [int] NOT NULL,
	[Detalle] [varchar](50) NOT NULL,
	[Precio] [int] NOT NULL,
	[Cantidad] [int] NOT NULL,
 CONSTRAINT [PK_PartesPorOrden] PRIMARY KEY CLUSTERED 
(
	[IdOrden] ASC,
	[IdProveedor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Persona]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Persona](
	[Nombre] [varchar](50) NOT NULL,
	[Cedula] [int] NOT NULL,
	[Direccion] [varchar](50) NOT NULL,
	[Ciudad] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Persona] PRIMARY KEY CLUSTERED 
(
	[Nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PreferenciasXCliente]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PreferenciasXCliente](
	[NombreCliente] [varchar](50) NOT NULL,
	[NombreMarca] [varchar](50) NOT NULL,
 CONSTRAINT [PK_PreferenciasXCliente] PRIMARY KEY CLUSTERED 
(
	[NombreCliente] ASC,
	[NombreMarca] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Proveedor]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Proveedor](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Direccion] [varchar](50) NOT NULL,
	[Ciudad] [varchar](50) NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[NombreContacto] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Proveedor] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ProveedoresDePartes]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ProveedoresDePartes](
	[IDProveedor] [int] NOT NULL,
	[NombreParte] [varchar](50) NOT NULL,
	[PorcentajeDeGanancia] [int] NOT NULL,
	[Precio] [int] NOT NULL,
 CONSTRAINT [PK_ProveedoresDePartes] PRIMARY KEY CLUSTERED 
(
	[IDProveedor] ASC,
	[NombreParte] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TelefonoProveedor]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TelefonoProveedor](
	[IDProveedor] [int] IDENTITY(1,1) NOT NULL,
	[Numero] [varchar](50) NOT NULL,
 CONSTRAINT [PK_TelefonoProveedor] PRIMARY KEY CLUSTERED 
(
	[IDProveedor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Telefonos]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Telefonos](
	[NombrePersona] [varchar](50) NOT NULL,
	[Numero] [int] NOT NULL,
 CONSTRAINT [PK_Telefonos_1] PRIMARY KEY CLUSTERED 
(
	[NombrePersona] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Engargado]  WITH CHECK ADD  CONSTRAINT [FK_Engargado_Organizaciones] FOREIGN KEY([Organizacion])
REFERENCES [dbo].[Organizaciones] ([Nombre])
GO
ALTER TABLE [dbo].[Engargado] CHECK CONSTRAINT [FK_Engargado_Organizaciones]
GO
ALTER TABLE [dbo].[MarcasDePartes]  WITH CHECK ADD  CONSTRAINT [FK_MarcasDePartes_Marca1] FOREIGN KEY([NombreMarca])
REFERENCES [dbo].[Marca] ([Nombre])
GO
ALTER TABLE [dbo].[MarcasDePartes] CHECK CONSTRAINT [FK_MarcasDePartes_Marca1]
GO
ALTER TABLE [dbo].[MarcasDePartes]  WITH CHECK ADD  CONSTRAINT [FK_MarcasDePartes_Parte] FOREIGN KEY([NombreParte])
REFERENCES [dbo].[Parte] ([Nombre])
GO
ALTER TABLE [dbo].[MarcasDePartes] CHECK CONSTRAINT [FK_MarcasDePartes_Parte]
GO
ALTER TABLE [dbo].[Orden]  WITH CHECK ADD  CONSTRAINT [FK_Orden_Cliente] FOREIGN KEY([Cliente])
REFERENCES [dbo].[Cliente] ([Nombre])
GO
ALTER TABLE [dbo].[Orden] CHECK CONSTRAINT [FK_Orden_Cliente]
GO
ALTER TABLE [dbo].[Organizaciones]  WITH CHECK ADD  CONSTRAINT [FK_Organizaciones_Cliente] FOREIGN KEY([Nombre])
REFERENCES [dbo].[Cliente] ([Nombre])
GO
ALTER TABLE [dbo].[Organizaciones] CHECK CONSTRAINT [FK_Organizaciones_Cliente]
GO
ALTER TABLE [dbo].[Organizaciones]  WITH CHECK ADD  CONSTRAINT [FK_Organizaciones_Engargado] FOREIGN KEY([IDEncargado])
REFERENCES [dbo].[Engargado] ([ID])
GO
ALTER TABLE [dbo].[Organizaciones] CHECK CONSTRAINT [FK_Organizaciones_Engargado]
GO
ALTER TABLE [dbo].[Parte]  WITH CHECK ADD  CONSTRAINT [FK_Parte_Fabricante] FOREIGN KEY([Fabricante])
REFERENCES [dbo].[Fabricante] ([Nombre])
GO
ALTER TABLE [dbo].[Parte] CHECK CONSTRAINT [FK_Parte_Fabricante]
GO
ALTER TABLE [dbo].[PartesDeAutomovil]  WITH CHECK ADD  CONSTRAINT [FK_PartesDeAutomovil_Automovil] FOREIGN KEY([ModeloAutomovil])
REFERENCES [dbo].[Automovil] ([Modelo])
GO
ALTER TABLE [dbo].[PartesDeAutomovil] CHECK CONSTRAINT [FK_PartesDeAutomovil_Automovil]
GO
ALTER TABLE [dbo].[PartesDeAutomovil]  WITH CHECK ADD  CONSTRAINT [FK_PartesDeAutomovil_Parte] FOREIGN KEY([NombreParte])
REFERENCES [dbo].[Parte] ([Nombre])
GO
ALTER TABLE [dbo].[PartesDeAutomovil] CHECK CONSTRAINT [FK_PartesDeAutomovil_Parte]
GO
ALTER TABLE [dbo].[PartesPorOrden]  WITH CHECK ADD  CONSTRAINT [FK_PartesPorOrden_Orden] FOREIGN KEY([IdOrden])
REFERENCES [dbo].[Orden] ([ID])
GO
ALTER TABLE [dbo].[PartesPorOrden] CHECK CONSTRAINT [FK_PartesPorOrden_Orden]
GO
ALTER TABLE [dbo].[PartesPorOrden]  WITH CHECK ADD  CONSTRAINT [FK_PartesPorOrden_Proveedor] FOREIGN KEY([IdProveedor])
REFERENCES [dbo].[Proveedor] ([ID])
GO
ALTER TABLE [dbo].[PartesPorOrden] CHECK CONSTRAINT [FK_PartesPorOrden_Proveedor]
GO
ALTER TABLE [dbo].[PreferenciasXCliente]  WITH CHECK ADD  CONSTRAINT [FK_PreferenciasXCliente_Cliente] FOREIGN KEY([NombreCliente])
REFERENCES [dbo].[Cliente] ([Nombre])
GO
ALTER TABLE [dbo].[PreferenciasXCliente] CHECK CONSTRAINT [FK_PreferenciasXCliente_Cliente]
GO
ALTER TABLE [dbo].[PreferenciasXCliente]  WITH CHECK ADD  CONSTRAINT [FK_PreferenciasXCliente_Marca] FOREIGN KEY([NombreMarca])
REFERENCES [dbo].[Marca] ([Nombre])
GO
ALTER TABLE [dbo].[PreferenciasXCliente] CHECK CONSTRAINT [FK_PreferenciasXCliente_Marca]
GO
ALTER TABLE [dbo].[ProveedoresDePartes]  WITH CHECK ADD  CONSTRAINT [FK_ProveedoresDePartes_Parte] FOREIGN KEY([NombreParte])
REFERENCES [dbo].[Parte] ([Nombre])
GO
ALTER TABLE [dbo].[ProveedoresDePartes] CHECK CONSTRAINT [FK_ProveedoresDePartes_Parte]
GO
ALTER TABLE [dbo].[ProveedoresDePartes]  WITH CHECK ADD  CONSTRAINT [FK_ProveedoresDePartes_Proveedor] FOREIGN KEY([IDProveedor])
REFERENCES [dbo].[Proveedor] ([ID])
GO
ALTER TABLE [dbo].[ProveedoresDePartes] CHECK CONSTRAINT [FK_ProveedoresDePartes_Proveedor]
GO
ALTER TABLE [dbo].[TelefonoProveedor]  WITH CHECK ADD  CONSTRAINT [FK_TelefonoProveedor_Proveedor] FOREIGN KEY([IDProveedor])
REFERENCES [dbo].[Proveedor] ([ID])
GO
ALTER TABLE [dbo].[TelefonoProveedor] CHECK CONSTRAINT [FK_TelefonoProveedor_Proveedor]
GO
ALTER TABLE [dbo].[Telefonos]  WITH CHECK ADD  CONSTRAINT [FK_Telefonos_Persona] FOREIGN KEY([NombrePersona])
REFERENCES [dbo].[Persona] ([Nombre])
GO
ALTER TABLE [dbo].[Telefonos] CHECK CONSTRAINT [FK_Telefonos_Persona]
GO
/****** Object:  StoredProcedure [dbo].[AssociateBrandAndPart]    Script Date: 08/04/2015 02:04:11 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Joshua>
-- Create date: <2,4,15>
-- Description:	<asocia marca con parte>
-- =============================================
CREATE PROCEDURE [dbo].[AssociateBrandAndPart]
	-- Add the parameters for the stored procedure here
	@PartName VARCHAR(50),
	@BrandName VARCHAR(50)
AS
BEGIN
	INSERT INTO [MarcasDePartes] (NombreParte,NombreMarca) 
		SELECT p.Nombre,m.Nombre
		FROM Parte p,Marca m
			WHERE	p.Nombre = @PartName AND m.Nombre = @BrandName
END

GO
